import React, {Component} from "react";
import Select from 'react-select';
import 'react-select/dist/react-select.css';
import axios from 'axios';
import {css, StyleSheet} from "aphrodite";
import ReactTable from 'react-table'
import "react-table/react-table.css";
import {Option} from "../utils/Option"
import Loader from "react-loader"
import {ReportTableSchema} from "../schema/ReportTableSchema";
import DatePicker from 'react-datepicker';
import moment from "moment/moment";

export default class THR extends Component {

    constructor() {
        super();
        this.state = {
            selectedOption: '',
            selectedDate: moment(),
            options: [],
            reportData: [],
            loaded: false
        }
    }


    addImageLink = record => {
        return Object.assign({}, record, {
            image: <a style={{cursor: "pointer"}} onClick={_ => this.addImage(record)}>View Image</a>
        })
    };

    isSameCode = (r, record) => (r.studentcode === record.studentcode && r.schoolcode === record.schoolcode && r.timestamp === record.timestamp)

    addImage = record => {
        const MIME = "data:image/jpeg;base64,";
        const dateTimeArr = record.timestamp.split("-")
        const date = `${dateTimeArr[0]}-${dateTimeArr[1]}-${dateTimeArr[2]}`
        const time = dateTimeArr[3]
        axios.get(`/bfr/thr/student-image/${record.schoolcode}/${record.studentcode}/${date}/${time}`).then(res => {
            const image = <img src={MIME + res.data} style={{"height": "40px", "width": "40px"}}/>;
            const newRecord = Object.assign({}, record, {"image": image})

            this.setState(prevState => ({
                reportData: prevState.reportData.map(r => this.isSameCode(r, record) ? newRecord : r)
            }))
        })
    }


    onHandleChange = selectedOption => {
        this.setState({loaded: false});
        Option(selectedOption).fold(
            _ => this.setState({selectedOption: '', reportData: [], loaded: true}),
            _ => {
                axios.get(`/bfr/thr/log/${selectedOption.value}/${this.state.selectedDate.format("DD-MM-YYYY")}`).then(res => {
                    this.setState({
                        selectedOption,
                        reportData: res.data.data.map(this.addImageLink),
                        loaded: true
                    })
                }).catch(err => {
                    this.setState({loaded: true})
                })

            })

    }

    handleSelectedDate = date => {
        Option(this.state.selectedOption)
            .fold(
                _ => this.setState({selectedDate: date}),
                _ => {
                    this.setState({loaded: false})
                    axios.get(`/bfr/thr/log/${this.state.selectedOption.value}/${date.format("DD-MM-YYYY")}`)
                        .then(res => {
                            this.setState({
                                selectedDate: date,
                                reportData: res.data.data.map(this.addImageLink),
                                loaded:true
                            })
                        }).catch(err => {
                        this.setState({loaded: true})
                    })
                })
    }

    render() {
        const {options} = this.state;
        let selectedOption = this.state.selectedOption;
        const value = selectedOption && selectedOption.value;
        return (
            <section class="wrapper state-overview">
                <Loader loaded={this.state.loaded} top="50%" left="55%">
                    <Select
                        style={{width: "95%"}}
                        value={value}
                        onChange={this.onHandleChange}
                        options={options}
                    />
                    <div>
                        <DatePicker id="selectedDate" name="selectedDate" required
                                    selected={this.state.selectedDate}
                                    onChange={this.handleSelectedDate}
                                    dateFormat="DD-MM-YYYY"
                        />
                    </div>
                    <ReactTable
                        style={{width: "95%", marginTop: "2%"}}
                        data={this.state.reportData}
                        columns={ReportTableSchema}
                        filterable
                        defaultPageSize={5}
                        className="-striped -highlight"
                    />
                </Loader>
            </section>
        )
    }

    componentDidMount = () => {
        axios.get('/amr/dropdown').then(({data}) => {
            this.setState({
                options: data,
                loaded: true
            })
        }).catch(err => {
            this.setState({loaded: true})
        })
    }

}