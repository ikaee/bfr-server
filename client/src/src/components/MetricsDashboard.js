import React from 'react';
import {Metrics} from "../schema/Metrics";

const MetricsDashboard = ({metricsType, metricsData}) => {
    const type = metricsType === "gmr" ? "gmr" : "normal"
    const metrics = Metrics[type](metricsData)
    return <div>
        <div className="row state-overview">
            {metrics.map(m =>
                <div className={`col-lg-${12/metrics.length} col-sm-6`}>
                    <section className="panel">
                        <div className={`symbol ${m.color}`}>
                            <i className={`fa ${m.type}`}></i>
                        </div>
                        <div class="value">
                            <h1>{m[m.name]}</h1>
                            <p><label>{m.name}</label></p>
                        </div>
                    </section>
                </div>
            )}
        </div>
    </div>};

export default MetricsDashboard;