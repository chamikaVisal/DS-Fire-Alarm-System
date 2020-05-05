import React, { Component } from 'react';
import { AiFillCheckCircle } from "react-icons/ai";
import { AiFillWarning } from "react-icons/ai";
import { Table } from 'react-bootstrap';
import { MdWifi } from "react-icons/md";

import './App.css';
import axios from 'axios';

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = { sensoeDetails: [] }

  }
  componentDidMount() {
    this.loadData();
  }

  loadData = () => {


    let baseURL = "http://localhost:8089";
    axios({

      method: 'GET',
      url: baseURL + '/sensors',


    })
      .then(response => {
        this.setState({
          sensoeDetails: response.data
        })
        console.log("success")
        console.log(this.state.sensoeDetails)
        var len = this.state.sensoeDetails.length;

        console.log(len + "this is the lenth")

      })
    //setting the auto refresh time as every 40 secs
    //.then(setInterval(this.loadData, 40000))


  }

  showStatus(sensor) {
    if (sensor.smokeLvl >= 5 || sensor.co2Lvl >= 5) {
      return (
        <div style={{ flexDirection: 'row ', fontWeight: 'bold', color: '#ff1919  ', borderColor: 'black' }}>DANGER</div>
      )
    }
    else {
      return (
        <div style={{ flexDirection: 'row ', fontWeight: 'bold', color: '#00D857  ', }}>NORMAL</div>
      )
    }

  }
  showIcon(sensor) {
    if (sensor.smokeLvl >= 5 || sensor.co2Lvl >= 5) {
      return (
        <AiFillWarning size={60} style={{ color: '#f20c0c' }} />
      )
    }
    else {
      return (
        <AiFillCheckCircle size={60} style={{ color: 'green' }} />
      )
    }

  }

  render() {

    return (
      /*PREVIOUS INTERFACE*/

      // <div style={{ textAlign: 'center', backgroundColor: 'grren' }}>
      //   <header style={{ minHeight: '100vh', display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center' }}>

      //     <h1 style={{}}>SENSOR INFORMATION</h1>
      //     {this.state.sensoeDetails.map(sensor => (
      //       <div style={{
      //         width: 620, height: 160, borderRadius: 30,
      //         marginLeft: 10, marginRight: 10, marginTop: 10, backgroundColor: '#2d2d2d'
      //       }}>

      //         <div style={{
      //           width: 600, height: 140, borderRadius: 30, justifyContent: 'center', alignItems: 'center', display: 'flex',
      //           marginLeft: 10, marginRight: 10, backgroundColor: sensor.co2Lvl >= 5 || sensor.smokeLvl >= 5 ? '#ff1919' : '#00D857'
      //         }}>
      //           <div style={{ padding: 10, flex: 2 }}>
      //             <div style={{ fontWeight: 'bold' }}>Sensor ID  :{sensor.sensorId}</div>
      //             <div style={{ fontWeight: 'bold' }}>Co2 Level  :{sensor.co2Lvl}</div>
      //             <div style={{ fontWeight: 'bold' }}>Smoke Level:{sensor.smokeLvl}</div>
      //             <div style={{ backgroundColor: "#ffffff", height: 40, width: 120, padding: 5, borderRadius: 30, marginTop: 5 }}>
      //               <div style={{ marginLeft: 10 }}>Status   :{this.showStatus(sensor)}</div>
      //             </div>

      //           </div>
      //           <div style={{ flex: 1 }}>
      //             {this.showIcon(sensor)}

      //           </div>

      //         </div>
      //       </div>
      //     ))}



      //   </header>
      // </div>



      /*NEW INTERFACE*/

      <div style={{ minHeight: '100vh', backgroundColor: '#ebedf5', justifyContent: 'center', alignItems: 'center' }}>

        <div style={{ paddingTop: 50, marginLeft: 120, marginRight: 120 }}>
          <h1 style={{ backgroundColor: '#254259', padding: 20, color: '#ffffff' }}>Moniter Sensor Information Here        <MdWifi size={60} style={{ color: '#ffffff' }} /></h1>

          <Table className="mt-4" striped bordered hover size="sm" >
            <thead>
              <tr style={{ fontWeight: "bold", color: '#ffffff', fontSize: 20, backgroundColor: '#254259' }}>
                <td>TID</td>
                <td>Sensor ID</td>
                <td>Floor No</td>
                <td>Room No</td>
                <td>Co2 Level</td>
                <td>Smoke Level</td>
                <td>Status</td>
              </tr>

            </thead>
            <tbody>
              {this.state.sensoeDetails.map(sensor =>
                <tr style={{ backgroundColor: sensor.co2Lvl >= 5 || sensor.smokeLvl >= 5 ? '#ff9e9e' : '#a6ffa9', fontSize: 20, padding: 20, height: 80, fontWeight: 'bold', color: '#25425' }}>
                  <td>{sensor.id}</td>
                  <td>{sensor.sensorId}</td>
                  <td>{sensor.floorNo}</td>
                  <td>{sensor.roomNo}</td>
                  <td>{sensor.co2Lvl}</td>
                  <td>{sensor.smokeLvl}</td>
                  <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center' }}>
                    <td style={{ flex: 4, justifyContent: 'center', alignItems: 'center' }} >{this.showStatus(sensor)}
                    </td>
                    <div style={{ flex: 2, justifyContent: 'center', alignItems: 'center' }}> {this.showIcon(sensor)}</div>
                  </div>

                </tr>
              )}
            </tbody>


          </Table>
        </div>

      </div>



    );
  }
}
