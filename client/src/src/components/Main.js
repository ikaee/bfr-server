import React from "react";
import {BrowserRouter as Router, Route} from 'react-router-dom';
import DocumentTitle from "react-document-title";
import faviconImage from "../images/icds.ico";
import Favicon from "react-favicon";
import Dashboard from "./DashBoard";
import AMR from "./AMR";
import THR from "./THR";
import NavMenu from "./NavMenu";
import Header from "./Header";
import Footer from "./Footer";
import Admin from "./Admin";
import HotCooked from "./HotCooked";
import moment from 'moment';
import GMR from "./GMR";
import {PROJECT_NAME} from "../utils/Configuration";

export default () =>
    <Router>
        <section id="container">
            <DocumentTitle title={PROJECT_NAME}/>
            <Favicon url={faviconImage}/>
            <Header title={PROJECT_NAME} date={`${moment().format("MMMM")} ${moment().format("YYYY")}`} />
            <NavMenu/>
            <section id={"main-content"}>
                <Route exact path="/" component={Dashboard}/>
                <Route path="/amr" component={AMR}/>
                <Route path="/thr" component={THR}/>
                <Route path="/hot-cooked" component={HotCooked}/>
                <Route path="/gmr" component={GMR}/>
                <Route path="/admin" component={Admin}/>
            </section>
            {/*<Footer />*/}
        </section>
    </Router>


