package data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jfree.data.xy.XYDataset;

@Getter
@Builder(toBuilder = true)
public class ChartParam {
    private String titleWindow;
    private String titleChart;
    private String xAxisLabel;
    private String yAxisLabel;
    private XYDataset dataset;
}
