import React, {Component} from "react";
import {Link} from "react-router-dom";
import {NavMenuSchema} from "../schema/NavMenuSchema"
export default class NavMenu extends Component {
    constructor() {
        super();
        this.state = {
            linkKey: 1
        }
    }

    activeClass = key => this.state.linkKey === key ? "active" : ""
    activeKey = linkKey => this.setState({linkKey});

    render() {
        return (
            <aside>
                <div id="sidebar" className="nav-collapse ">
                    <ul className="sidebar-menu" id="nav-accordion">
                        {NavMenuSchema.map(menu =>
                            <li>
                                <Link className={this.activeClass(menu.id)} onClick={() => this.activeKey(menu.id)}
                                      to={menu.uri}>
                                    <i className={menu.class}></i>
                                    <span>{menu.name}</span>
                                </Link>
                            </li>
                        )}
                    </ul>
                </div>
            </aside>

        )
    }
}
