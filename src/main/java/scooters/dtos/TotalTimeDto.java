package scooters.dtos;

public class TotalTimeDto {
	private Integer scooterId;
	private Long totalTimeSeconds;
    public TotalTimeDto() {
    }
    public TotalTimeDto(Integer scooterId, Long totalTimeSeconds) {
        this.scooterId = scooterId;
        this.totalTimeSeconds = totalTimeSeconds;
    }
    public Integer getScooterId() {
        return scooterId;
    }
    public void setScooterId(Integer scooterId) {
        this.scooterId = scooterId;
    }
    public Long getTotalTimeSeconds() {
        return totalTimeSeconds;
    }
    public void setTotalTimeSeconds(Long totalTimeSeconds) {
        this.totalTimeSeconds = totalTimeSeconds;
    }

    
}
