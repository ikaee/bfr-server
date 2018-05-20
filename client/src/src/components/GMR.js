import React, {Component} from "react";
import Select from 'react-select';
import 'react-select/dist/react-select.css';
import {css, StyleSheet} from "aphrodite";
import ReactTable from 'react-table'
import "react-table/react-table.css";
import Loader from "react-loader";
import 'react-datepicker/dist/react-datepicker.css';
import axios from "axios/index";
import {Option} from "../utils/Option";

export const reportTableColumns = [
    {
        Header: 'Code',
        accessor: 'code'
    }, {
        Header: 'Name',
        accessor: 'name'
    }, {
        Header: 'Surname',
        accessor: 'surname'
    }, {
        Header: 'Gender',
        accessor: 'gender'
    }, {
        Header: 'Dob',
        accessor: 'dob'
    }, {
        Header: 'Class',
        accessor: 'class'
    }, {
        Header: 'Log Date',
        accessor: 'logDates'
    }, {
        Header: 'Weight(kg)',
        accessor: 'weights'
    }, {
        Header: 'Height(cm)',
        accessor: 'height'
    },{
        Header: 'WHO',
        accessor: 'whoGrades'
    }]

class GMR extends Component {

    constructor() {
        super();
        this.state = {
            selectedOption: '',
            options: [],
            reportData: [],
            loaded: false
        }
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

    onHandleChange = selectedOption => {
        this.setState({loaded: false});
        Option(selectedOption).fold(
            _ => this.setState({selectedOption: '', reportData: [], loaded: true}),
            _ => {
                axios.get(`http://epgmweb.centralindia.cloudapp.azure.com:8080/epgm/gmreport/${selectedOption.value}`).then(res => {
                    this.setState({
                        selectedOption,
                        reportData: res.data.data,
                        loaded: true
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
            <section className="wrapper state-overview">
                <Loader loaded={this.state.loaded} top="50%" left="55%">
                    <Select
                        style={{width: "95%"}}
                        value={value}
                        onChange={this.onHandleChange}
                        options={options}
                    />
                    <ReactTable
                        style={{width: "95%", marginTop: "2%"}}
                        data={this.state.reportData}
                        columns={reportTableColumns}
                        filterable
                        defaultPageSize={5}
                        className="-striped -highlight"
                    />
                </Loader>
            </section>
        )
    }

}

export default GMR;