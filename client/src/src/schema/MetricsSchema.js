import {Option} from "../utils/Option";

const extractValue = (data, key) => Option(data).fold(_ => 0, _ => data[key])
export const MetricsSchema = {
    "gmr": data => [
        {
            "type":"fa-user",
            "name":"Weighed",
            "color":"blue",
            "Weighed": extractValue(data, "total")
        },{
            "type":"fa-user",
            "name":"Severly Malnourished",
            "color":"red",
            "Severly Malnourished": extractValue(data, "severe")
        },{
            "type":"fa-user",
            "name":"Moderately Malnourished",
            "color":"yellow",
            "Moderately Malnourished": extractValue(data, "moderate")
        },{
            "type":"fa-user",
            "name":"Normal",
            "color":"green",
            "Normal": extractValue(data, "normal")
        }
    ],
    "normal": data => [
        {
            "type":"fa-hand-pointer-o",
            "name":"Present",
            "color":"terques",
            "Present": extractValue(data, "present")
        },{
            "type":"fa-users",
            "name":"Total",
            "color":"red",
            "Total": extractValue(data, "total")
        },{
            "type":"fa-percent",
            "name":"Percentage",
            "color":"yellow",
            "Percentage": extractValue(data, "percentage")
        }
    ]
}