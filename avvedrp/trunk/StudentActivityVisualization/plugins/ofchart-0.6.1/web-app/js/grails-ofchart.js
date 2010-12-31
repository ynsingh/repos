/**
 * Reload chart with new data.
 * @param chart the chart id
 * @param data json data, in string format
 */
function uploadChart(chart,data) {
    $(chart).load(data);
}