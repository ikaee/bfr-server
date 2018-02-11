import React, {Component} from "react";
import Loader from "react-loader";
import Select from 'react-select';
import 'react-select/dist/react-select.css';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';

export default class Admin extends Component {
    constructor() {
        super();
        this.state = {
            selectedOption: '',
            options: [],
            gender: "M",
            dob: moment(),
            loaded: false,
            notify: ""
        }
    }

    onHandleChange = selectedOption => this.setState({selectedOption})
    onChange = data => this.setState({...data})

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

    handleSubmit = e => {
        e.preventDefault();
        const schoolcode = this.state.selectedOption.value;
        const studentcode = this.refs.studentcode.value;
        const name = this.refs.firstname.value;
        const surname = this.refs.surname.value;
        const gender = this.state.gender;
        const dob = this.state.dob.format("DD-MM-YYYY");
        const doctype = "registration";
        const registration = {schoolcode, studentcode, name, surname, gender, dob, doctype};

        this.setState({loaded: false});
        axios.post("/bfr/v1/registration", registration).then(data => {
            this.setState({loaded: true, notify: "Registration Successful", selectedOption: '', dob: moment()});
        }).catch(err => {
            this.setState({loaded: true, notify: "Registration Failed", selectedOption: '', dob: moment()});
        })
    }
    cancelSubmit = _ => {
        this.refs.schoolcode.value = "";
        this.refs.studentcode.value = "";
        this.refs.firstname.value = "";
        this.refs.surname.value = "";
        this.refs.gender.value = "";
        this.refs.dob.value = "";
    }

    render() {
        const {options} = this.state;
        let selectedOption = this.state.selectedOption;
        const value = selectedOption && selectedOption.value;

        return (
            <Loader loaded={this.state.loaded}>
                <section className="wrapper">
                    <div className="row">
                        <div className="col-lg-12">
                            <section className="panel">
                                <header className="panel-heading">
                                    Registration
                                </header>
                                <div className="panel-body">
                                    <div className="form">
                                        <form className="cmxform form-horizontal tasi-form" id="signupForm" method="get"
                                              action="" onSubmit={this.handleSubmit}>
                                            <div className="form-group ">
                                                <label for="schoolcode" className="control-label col-lg-2">Code</label>
                                                <div className="col-lg-10">
                                                    <Select id="schoolcode" name="schoolcode" ref="schoolcode" required
                                                            style={{width: "95%"}}
                                                            value={value}
                                                            onChange={this.onHandleChange}
                                                            options={options}
                                                    />
                                                </div>
                                            </div>
                                            <div className="form-group ">
                                                <label for="studentcode" className="control-label col-lg-2">Beneficiary
                                                    Code</label>
                                                <div className="col-lg-10">
                                                    <input className=" form-control" id="studentcode" name="studentcode"
                                                           type="text" ref="studentcode" required/>
                                                </div>
                                            </div>
                                            <div className="form-group ">
                                                <label for="firstname"
                                                       className="control-label col-lg-2">Firstname</label>
                                                <div className="col-lg-10">
                                                    <input className=" form-control" id="firstname" name="firstname"
                                                           type="text" ref="firstname" required/>
                                                </div>
                                            </div>
                                            <div className="form-group ">
                                                <label for="surname" className="control-label col-lg-2">Surname</label>
                                                <div className="col-lg-10">
                                                    <input className=" form-control" id="surname" name="surname"
                                                           type="text" ref="surname" required/>
                                                </div>
                                            </div>
                                            <div className="form-group ">
                                                <label for="gender" className="control-label col-lg-2">Gender</label>
                                                <div class="col-lg-10">
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" id="gender" name="gender" value="M"
                                                                   onChange={_ => this.onChange({gender: "M"})}  />
                                                            M
                                                        </label>
                                                    </div>

                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" id="gender" name="gender" value="F"
                                                                   ref="gender"
                                                                   onChange={_ => this.onChange({gender: "F"})}/>
                                                            F
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="form-group ">
                                                <label for="dob" className="control-label col-lg-2">DOB</label>
                                                <div className="col-lg-10">
                                                    <DatePicker id="dob" name="dob" required
                                                                selected={this.state.dob}
                                                                onChange={date => this.onChange({dob: date})}
                                                                dateFormat="DD-MM-YYYY"
                                                    />
                                                </div>
                                            </div>
                                            <div className="form-group">
                                                <div className="col-lg-offset-2 col-lg-10">
                                                    <button className="btn btn-danger" type="submit" value="Submit">
                                                        Submit
                                                    </button>
                                                    <button className="btn btn-default" type="button"
                                                            onClick={this.cancelSubmit}>Cancel
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                    <label>{this.state.notify}</label>
                </section>
            </Loader>
        )
    }
}