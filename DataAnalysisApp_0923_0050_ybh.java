// 代码生成时间: 2025-09-23 00:50:06
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class DataAnalysisApp implements Serializable {

    private List<Double> dataPoints = new ArrayList<>();
    private double sum = 0;
    private double average = 0;
    private double max = Double.MIN_VALUE;
    private double min = Double.MAX_VALUE;

    // Constructor
    public DataAnalysisApp() {
        // Initialize data points for demonstration
        dataPoints.add(10.0);
        dataPoints.add(20.0);
        dataPoints.add(30.0);
        dataPoints.add(40.0);
        dataPoints.add(50.0);
    }

    // Method to calculate statistical data
    public void calculateStatistics() {
        for (Double value : dataPoints) {
            sum += value;
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }
        average = sum / dataPoints.size();
    }

    // Getters and Setters
    public List<Double> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<Double> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    // Action method to handle form submission
    public String analyzeData() {
        try {
            calculateStatistics();
            return "success"; // Redirect to a success page or message
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error calculating statistics: " + e.getMessage()));
            return "error"; // Redirect to an error page or message
        }
    }

    // Add a data point to the list
    public void addDataPoint(double value) {
        dataPoints.add(value);
    }
}
