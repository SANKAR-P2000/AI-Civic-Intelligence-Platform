import { useState } from "react";
import Button from "../ui/Button.jsx";
import Input from "../ui/Input.jsx";
import "./ComplaintSearchFilter.css";

const CATEGORIES = [
  "ROAD_DAMAGE",
  "STREET_LIGHT",
  "GARBAGE",
  "WATER_SUPPLY",
  "DRAINAGE",
  "TRAFFIC",
  "PUBLIC_TRANSPORT",
  "ENVIRONMENT",
  "ANIMAL_CONTROL",
  "OTHER",
];

const STATUSES = ["PENDING", "IN_PROGRESS", "RESOLVED", "REJECTED"];

function ComplaintSearchFilter({ onApplyFilters, onClearFilters, loading }) {
  const [keyword, setKeyword] = useState("");
  const [category, setCategory] = useState("");
  const [status, setStatus] = useState("");
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [city, setCity] = useState("");
  const [sortBy, setSortBy] = useState("createdAt");
  const [sortDirection, setSortDirection] = useState("desc");

  // Spatial Search State
  const [useSpatial, setUseSpatial] = useState(false);
  const [latitude, setLatitude] = useState("13.0827");
  const [longitude, setLongitude] = useState("80.2707");
  const [radiusKm, setRadiusKm] = useState("5");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (useSpatial) {
      onApplyFilters({
        isSpatial: true,
        latitude: parseFloat(latitude),
        longitude: parseFloat(longitude),
        radiusKm: parseFloat(radiusKm),
      });
    } else {
      onApplyFilters({
        isSpatial: false,
        keyword: keyword.trim() || undefined,
        category: category || undefined,
        status: status || undefined,
        city: city.trim() || undefined,
        fromDate: fromDate || undefined,
        toDate: toDate || undefined,
        sortBy,
        sortDirection,
      });
    }
  };

  const handleReset = () => {
    setKeyword("");
    setCategory("");
    setStatus("");
    setFromDate("");
    setToDate("");
    setCity("");
    setSortBy("createdAt");
    setSortDirection("desc");
    setUseSpatial(false);
    onClearFilters();
  };

  return (
    <form className="aicip-filter-panel" onSubmit={handleSubmit}>
      <div className="aicip-filter-header">
        <div className="aicip-filter-tabs">
          <button
            type="button"
            className={`aicip-filter-tab ${!useSpatial ? "active" : ""}`}
            onClick={() => setUseSpatial(false)}
          >
            🔍 Standard Filter
          </button>
          <button
            type="button"
            className={`aicip-filter-tab ${useSpatial ? "active" : ""}`}
            onClick={() => setUseSpatial(true)}
          >
            📍 Spatial Radius Search
          </button>
        </div>
      </div>

      {!useSpatial ? (
        <div className="aicip-filter-grid">
          <Input
            label="Keyword Search"
            placeholder="Search title, description, location..."
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
          />

          <div className="aicip-field">
            <label className="aicip-field__label" htmlFor="filter-category">
              Category
            </label>
            <select
              id="filter-category"
              className="aicip-field__input"
              value={category}
              onChange={(e) => setCategory(e.target.value)}
            >
              <option value="">All Categories</option>
              {CATEGORIES.map((cat) => (
                <option key={cat} value={cat}>
                  {cat.toLowerCase().replace(/_/g, " ")}
                </option>
              ))}
            </select>
          </div>

          <div className="aicip-field">
            <label className="aicip-field__label" htmlFor="filter-status">
              Status
            </label>
            <select
              id="filter-status"
              className="aicip-field__input"
              value={status}
              onChange={(e) => setStatus(e.target.value)}
            >
              <option value="">All Statuses</option>
              {STATUSES.map((st) => (
                <option key={st} value={st}>
                  {st.replace(/_/g, " ")}
                </option>
              ))}
            </select>
          </div>

          <Input
            label="City / Location"
            placeholder="e.g. Villupuram"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />

          <Input
            label="From Date"
            type="date"
            value={fromDate}
            onChange={(e) => setFromDate(e.target.value)}
          />

          <Input
            label="To Date"
            type="date"
            value={toDate}
            onChange={(e) => setToDate(e.target.value)}
          />

          <div className="aicip-field">
            <label className="aicip-field__label" htmlFor="filter-sort">
              Sort By
            </label>
            <select
              id="filter-sort"
              className="aicip-field__input"
              value={`${sortBy}_${sortDirection}`}
              onChange={(e) => {
                const [sb, sd] = e.target.value.split("_");
                setSortBy(sb);
                setSortDirection(sd);
              }}
            >
              <option value="createdAt_desc">Newest First</option>
              <option value="createdAt_asc">Oldest First</option>
              <option value="title_asc">Title A-Z</option>
              <option value="title_desc">Title Z-A</option>
            </select>
          </div>
        </div>
      ) : (
        <div className="aicip-spatial-grid">
          <Input
            label="Latitude"
            type="number"
            step="any"
            placeholder="e.g. 13.0827"
            value={latitude}
            onChange={(e) => setLatitude(e.target.value)}
            required
          />

          <Input
            label="Longitude"
            type="number"
            step="any"
            placeholder="e.g. 80.2707"
            value={longitude}
            onChange={(e) => setLongitude(e.target.value)}
            required
          />

          <div className="aicip-field">
            <label className="aicip-field__label" htmlFor="filter-radius">
              Search Radius
            </label>
            <select
              id="filter-radius"
              className="aicip-field__input"
              value={radiusKm}
              onChange={(e) => setRadiusKm(e.target.value)}
            >
              <option value="1">1 km</option>
              <option value="5">5 km</option>
              <option value="10">10 km</option>
              <option value="25">25 km</option>
              <option value="50">50 km</option>
            </select>
          </div>
        </div>
      )}

      <div className="aicip-filter-actions">
        <Button type="submit" loading={loading} size="sm">
          {useSpatial ? "Find Nearby Complaints" : "Apply Filters"}
        </Button>
        <Button type="button" variant="ghost" size="sm" onClick={handleReset}>
          Reset
        </Button>
      </div>
    </form>
  );
}

export default ComplaintSearchFilter;
