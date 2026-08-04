package com.sankar.aicip.service;

import com.sankar.aicip.dto.request.CreateComplaintRequest;
import com.sankar.aicip.dto.response.ComplaintResponse;
import com.sankar.aicip.entity.Complaint;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.enums.UserRole;
import com.sankar.aicip.exception.ResourceNotFoundException;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.repository.UserRepository;
import com.sankar.aicip.service.email.EmailService;
import com.sankar.aicip.service.impl.ComplaintServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class ComplaintServiceImplTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ComplaintServiceImpl complaintService;
    private User user;
    private Complaint complaint;
    private CreateComplaintRequest createComplaintRequest;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFullName("Sankar P");
        user.setEmail("sankar@example.com");
        user.setPhoneNumber("9876543210");
        user.setRole(UserRole.CITIZEN);

        complaint = new Complaint();
        complaint.setId(100L);
        complaint.setTitle("Road Damage");
        complaint.setDescription("Large pothole on main road");
        complaint.setCategory(ComplaintCategory.ROAD_DAMAGE);
        complaint.setLocation("Chennai");
        complaint.setStatus(ComplaintStatus.PENDING);
        complaint.setCitizen(user);
        createComplaintRequest = new CreateComplaintRequest();
        createComplaintRequest.setTitle("Road Damage");
        createComplaintRequest.setDescription("Large pothole on main road");
        createComplaintRequest.setCategory(ComplaintCategory.ROAD_DAMAGE);
        createComplaintRequest.setLocation("Chennai");
        createComplaintRequest.setImageUrl("road.jpg");
    }

    @Test
    void createComplaint_ShouldCreateSuccessfully() {

        // Arrange
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        null
                );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        when(complaintRepository.save(any(Complaint.class)))
                .thenReturn(complaint);

        doNothing().when(emailService)
                .sendComplaintSubmittedEmail(
                        anyString(),
                        anyString(),
                        anyLong(),
                        anyString(),
                        anyString()
                );

        // Act
        ComplaintResponse response =
                complaintService.createComplaint(createComplaintRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Road Damage", response.getTitle());
        assertEquals("Large pothole on main road", response.getDescription());
        assertEquals(ComplaintCategory.ROAD_DAMAGE, response.getCategory());
        assertEquals(ComplaintStatus.PENDING, response.getStatus());
        assertEquals("Chennai", response.getLocation());
        assertEquals("Sankar P", response.getCitizenName());
        assertEquals("sankar@example.com", response.getCitizenEmail());

        verify(userRepository, times(1))
                .findByEmail(user.getEmail());

        verify(complaintRepository, times(1))
                .save(any(Complaint.class));

        verify(emailService, times(1))
                .sendComplaintSubmittedEmail(
                        anyString(),
                        anyString(),
                        anyLong(),
                        anyString(),
                        anyString()
                );

        verifyNoMoreInteractions(
                userRepository,
                complaintRepository,
                emailService
        );

        SecurityContextHolder.clearContext();
    }

    @Test
    void createComplaint_ShouldThrowException_WhenUserNotFound() {

        // Arrange
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        null
                );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> complaintService.createComplaint(createComplaintRequest)
        );

        assertEquals("User not found.", exception.getMessage());

        verify(userRepository, times(1))
                .findByEmail(user.getEmail());

        verify(complaintRepository, never())
                .save(any(Complaint.class));

        verify(emailService, never())
                .sendComplaintSubmittedEmail(
                        anyString(),
                        anyString(),
                        anyLong(),
                        anyString(),
                        anyString()
                );

        verifyNoMoreInteractions(
                userRepository,
                complaintRepository,
                emailService
        );

        SecurityContextHolder.clearContext();
    }

    @Test
    void getMyComplaints_ShouldReturnComplaintListSuccessfully() {

        // Arrange
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        null
                );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        when(complaintRepository.findByCitizen(user))
                .thenReturn(List.of(complaint));

        // Act
        List<ComplaintResponse> response =
                complaintService.getMyComplaints();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());

        ComplaintResponse complaintResponse = response.get(0);

        assertEquals("Road Damage", complaintResponse.getTitle());
        assertEquals("Large pothole on main road", complaintResponse.getDescription());
        assertEquals(ComplaintCategory.ROAD_DAMAGE, complaintResponse.getCategory());
        assertEquals(ComplaintStatus.PENDING, complaintResponse.getStatus());

        verify(userRepository, times(1))
                .findByEmail(user.getEmail());

        verify(complaintRepository, times(1))
                .findByCitizen(user);

        verifyNoMoreInteractions(
                userRepository,
                complaintRepository
        );

        SecurityContextHolder.clearContext();
    }
    @Test
    void trackComplaint_ShouldReturnComplaintSuccessfully() {

        // Arrange
        when(complaintRepository.findById(100L))
                .thenReturn(Optional.of(complaint));

        // Act
        ComplaintResponse response =
                complaintService.trackComplaint(100L);

        // Assert
        assertNotNull(response);
        assertEquals(100L, response.getId());
        assertEquals("Road Damage", response.getTitle());
        assertEquals("Large pothole on main road", response.getDescription());
        assertEquals(ComplaintCategory.ROAD_DAMAGE, response.getCategory());
        assertEquals(ComplaintStatus.PENDING, response.getStatus());
        assertEquals("Chennai", response.getLocation());

        verify(complaintRepository, times(1))
                .findById(100L);

        verifyNoMoreInteractions(complaintRepository);
    }
    @Test
    void trackComplaint_ShouldThrowException_WhenComplaintNotFound() {

        // Arrange
        when(complaintRepository.findById(100L))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> complaintService.trackComplaint(100L)
        );

        assertEquals("Complaint not found.", exception.getMessage());

        verify(complaintRepository, times(1))
                .findById(100L);

        verifyNoMoreInteractions(complaintRepository);
    }
    @Test
    void getAllComplaints_ShouldReturnAllComplaintsSuccessfully() {

        // Arrange
        when(complaintRepository.findAll())
                .thenReturn(List.of(complaint));

        // Act
        List<ComplaintResponse> response =
                complaintService.getAllComplaints();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());

        ComplaintResponse complaintResponse = response.get(0);

        assertEquals(100L, complaintResponse.getId());
        assertEquals("Road Damage", complaintResponse.getTitle());
        assertEquals("Large pothole on main road", complaintResponse.getDescription());
        assertEquals(ComplaintCategory.ROAD_DAMAGE, complaintResponse.getCategory());
        assertEquals(ComplaintStatus.PENDING, complaintResponse.getStatus());
        assertEquals("Chennai", complaintResponse.getLocation());
        assertEquals("Sankar P", complaintResponse.getCitizenName());
        assertEquals("sankar@example.com", complaintResponse.getCitizenEmail());

        verify(complaintRepository, times(1))
                .findAll();

        verifyNoMoreInteractions(complaintRepository);
    }
    @Test
    void updateComplaintStatus_ShouldUpdateStatusSuccessfully() {

        // Arrange
        when(complaintRepository.findById(100L))
                .thenReturn(Optional.of(complaint));

        when(complaintRepository.save(any(Complaint.class)))
                .thenReturn(complaint);

        doNothing().when(emailService)
                .sendComplaintStatusUpdatedEmail(
                        anyString(),
                        anyString(),
                        anyLong(),
                        anyString()
                );

        // Act
        ComplaintResponse response =
                complaintService.updateComplaintStatus(
                        100L,
                        ComplaintStatus.RESOLVED
                );

        // Assert
        assertNotNull(response);
        assertEquals(100L, response.getId());
        assertEquals(ComplaintStatus.RESOLVED, response.getStatus());

        verify(complaintRepository, times(1))
                .findById(100L);

        verify(complaintRepository, times(1))
                .save(any(Complaint.class));

        verify(emailService, times(1))
                .sendComplaintStatusUpdatedEmail(
                        anyString(),
                        anyString(),
                        anyLong(),
                        eq("RESOLVED")
                );

        verifyNoMoreInteractions(
                complaintRepository,
                emailService
        );
    }
    @Test
    void updateComplaintStatus_ShouldThrowException_WhenComplaintNotFound() {

        // Arrange
        when(complaintRepository.findById(100L))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> complaintService.updateComplaintStatus(
                        100L,
                        ComplaintStatus.RESOLVED
                )
        );

        assertEquals("Complaint not found.", exception.getMessage());

        verify(complaintRepository, times(1))
                .findById(100L);

        verify(complaintRepository, never())
                .save(any(Complaint.class));

        verify(emailService, never())
                .sendComplaintStatusUpdatedEmail(
                        anyString(),
                        anyString(),
                        anyLong(),
                        anyString()
                );

        verifyNoMoreInteractions(
                complaintRepository,
                emailService
        );
    }
}