import React from "react";
import NavMenu from "../../src/components/NavMenu";
import Dashboard from "../../src/components/DashBoard";
import {shallow} from "enzyme";
import AMR from "../../src/components/AMR";
import Admin from "../../src/components/Admin";
import {Tab} from "pui-react-tabs";
import renderer from 'react-test-renderer';


describe('NavMenu component', () => {
    let navMenu = shallow(<NavMenu/>);

    it('should render', () => {
        shallow(<NavMenu/>)
    });

    it('render tabs in sequence', () => {
        const tabs = navMenu.props().children.props.children
        expect(tabs[0].props.title).toEqual("Dashboard")
        expect(tabs[1].props.title).toEqual("AMR")
        expect(tabs[2].props.title).toEqual("Admin")
    });

    it('render Dashboard tab by default',()=>{
        const dashboardTab =  <Tab eventKey={1} title="Dashboard">{<Dashboard />}</Tab>
        expect(navMenu.find("LeftTabs").props().defaultActiveKey).toEqual(1);
        expect(navMenu.find("LeftTabs")).toContainReact(dashboardTab);

    });

    it('has dashboard tab', () => {
        expect(navMenu).toContainReact(<Dashboard/>)
    });

    it('has AMR tab', () => {
        expect(navMenu).toContainReact(<AMR/>)
    });

    it('has Admin tab', () => {
        expect(navMenu).toContainReact(<Admin/>)
    });
})