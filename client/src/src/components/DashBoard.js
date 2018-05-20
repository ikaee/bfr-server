import React, {Component} from "react";
import GenderWise from "./graphs/GenderWise";
import MonthWise from "./graphs/MonthWise";
import AgeWise from "./graphs/AgeWise";
import MetricsDashboard from './MetricsDashboard';
import Select from 'react-select';
import 'react-select/dist/react-select.css';
import Loader from "react-loader";
import axios from 'axios';
import {Option} from "../utils/Option";
import DatePicker from 'react-datepicker';
import moment from "moment/moment";

class Dashboard extends Component {
    constructor() {
        super();
        this.state = {
            data: {},
            loaded: false,
            selectedDate: moment(),
            selectedOption: {value: 'attendance', label: 'Attendance'},
            options: [{value: 'attendance', label: 'Attendance'}, {
                value: "thr",
                label: "Take Home Ration"
            }, {value: 'hot-cooked', label: 'HotMeals'}, {value: "gmr", label: "Growth Monitoring Report"}]
        }
    }

    componentDidMount() {
        axios.get(`/bfr/v1/dashboard/${this.state.selectedOption.value}/${this.state.selectedDate.format("DD-MM-YYYY")}`)
            .then(({data}) => {
                this.setState({
                    loaded: true,
                    data
                })
            })
            .catch(err => {
                this.setState({loaded: true, data: {}})
            })
    }

    gmrDashboardData = (selectedOption, date) => {
        this.setState({loaded: false});
        Option(selectedOption).fold(
            _ => this.setState({selectedOption: '', data: {}, loaded: true}),
            _ => {
                axios.get(`http://epgmweb.centralindia.cloudapp.azure.com:8080/epgm/dashboard/27`).then(res => {
                    this.setState({
                        selectedOption,
                        data: {"attendance_data": res.data["grade_data"], ...res.data},
                        loaded: true
                    })
                }).catch(err => {
                    this.setState({loaded: true, data:{}})
                })

            })
    }

    regularDashboardData = selectedOption => {
        this.setState({loaded: false});
        Option(selectedOption).fold(
            _ => this.setState({selectedOption: '', data: {}, loaded: true}),
            _ => {
                axios.get(`/bfr/v1/dashboard/${selectedOption.value}/${this.state.selectedDate.format("DD-MM-YYYY")}`).then(res => {
                    this.setState({
                        selectedOption,
                        data: res.data,
                        loaded: true
                    })
                }).catch(err => {
                    this.setState({loaded: true})
                })

            })
    }

    onHandleChange = selectedOption => {

        this.setState({
            selectedOption,
            loaded: true,
            selectedDashboard: selectedOption.label
        });

        selectedOption.value === "gmr" ? this.gmrDashboardData(selectedOption) : this.regularDashboardData(selectedOption)

    }

    handleSelectedDate = date => {
        this.setState({loaded: false})

        axios.get(`/bfr/v1/dashboard/${this.state.selectedOption.value}/${date.format("DD-MM-YYYY")}`)
            .then(({data}) => {
                this.setState({
                    selectedDate: date,
                    data,
                    loaded: true
                })
            })
            .catch(err => {
                this.setState({loaded: true})
            })
    }

    render() {
        const {options} = this.state;
        let selectedOption = this.state.selectedOption;
        const value = selectedOption && selectedOption.value;
        const {age_data, attendance_data, gender_data, month_data} = this.state.data;
        return (

            <section className="wrapper">
                <Loader loaded={this.state.loaded} top="50%" left="55%">
                    <div>
                        <Select
                            style={{width: "95%"}}
                            value={value}
                            onChange={this.onHandleChange}
                            options={options}
                        />
                    </div>
                    <div>
                        { this.state.selectedOption.value !== "gmr" ? <DatePicker id="selectedDate" name="selectedDate" required
                                    selected={this.state.selectedDate}
                                    onChange={this.handleSelectedDate}
                                    dateFormat="DD-MM-YYYY"
                        /> : <div></div>}
                    </div>
                    <div style={{height: '20px', fontSize: '20px', paddingBottom: '30px'}}>
                        <label> {this.state.selectedOption.label} Dashboard</label>
                    </div>
                    <div>
                        <MetricsDashboard metricsType={this.state.selectedOption.value} metricsData={attendance_data}/>
                        <MonthWise title='Month wise' data={month_data}/>
                        <GenderWise title='Gender Wise' data={gender_data}/>
                        <AgeWise title={"Age wise"} data={age_data}/>
                    </div>
                </Loader>
            </section>
        )
    }
}

const data = [
    {
        value: 40,
        color: "#F7464A"
    },
    {
        value: 50,
        color: "#E2EAE9"
    }];
export default Dashboard;