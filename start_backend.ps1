# Start the Spring Boot backend with the local profile, detached from terminal.
# Logs are written to backend/backend-console.log for inspection.

$backendDir = "D:\projects\AI-Civic-Intelligence-Platform\backend"
Set-Location $backendDir

# If port 8080 is already in use, report and exit.
$inUse = netstat -ano | findstr :8080 | findstr LISTENING
if ($inUse) {
  Write-Output "Port 8080 is already in use:"
  Write-Output $inUse
  Write-Output "Please stop that process first (taskkill /PID <pid> /F)."
  exit 1
}

Write-Output "Starting backend with local profile on port 8080 ..."

# Start detached so it survives this terminal closing.
$proc = Start-Process -FilePath "cmd.exe" `
  -ArgumentList "/c mvnw spring-boot:run -Dspring-boot.run.profiles=local > backend-console.log 2>&1" `
  -WorkingDirectory $backendDir `
  -WindowStyle Hidden

Write-Output "Started (cmd PID $($proc.Id)). Waiting for boot ..."
Start-Sleep -Seconds 20

$again = netstat -ano | findstr :8080 | findstr LISTENING
if ($again) {
  Write-Output "SUCCESS: Backend is now LISTENING on 8080:"
  Write-Output $again
} else {
  Write-Output "WARNING: Port 8080 not yet listening. Check backend/backend-console.log"
}
