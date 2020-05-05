import React, { Component } from 'react';
import { MdWifi } from "react-icons/md";
import axios from 'axios';

import './App.css';
export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {

      co2level0: '',
      smokelevel0: '',
      response0: '',


      co2level1: '',
      smokelevel1: '',
      response1: '',

      co2level2: '',
      smokelevel2: '',
      id: '',
      senid: '',
      response2: '',

      responseSensor0: '',
      responseSensor1: '',
      responseSensor2: '',



    };

  }
  //sensor 0---------------------------------------------------------------------------
  updateco2ValueSensor0 = event => {
    event.preventDefault()
    console.log(event.target.value)
    var val = event.target.value
    console.log(val + "this is the value retrived")
    this.setState({
      co2level0: parseInt(event.target.value)
    }, () => {
      console.log("New state in ASYNC callback:", this.state.co2level0);
    });

    console.log("New state DIRECTLY after setState:", this.state.co2level0);
    console.log("New state DIRECTLY after setState:", this.state.co2level0);



  }
  updatesmokeValueSensor0 = event => {
    event.preventDefault()
    console.log(event.target.value)
    var val = event.target.value
    console.log(val + "this is the value retrived")
    this.setState({
      smokelevel0: parseInt(event.target.value)
    }, () => {
      console.log("New state in ASYNC callback:", this.state.smokelevel0);
    });

    console.log("New state DIRECTLY after setState:", this.state.smokelevel0);



  }
  //sensor 1---------------------------------------------------------------------------
  updateco2ValueSensor1 = event => {
    event.preventDefault()
    console.log(event.target.value)
    var val = event.target.value
    console.log(val + "this is the value retrived")
    this.setState({
      co2level1: parseInt(event.target.value)
    }, () => {
      console.log("New state in ASYNC callback:", this.state.co2level1);
    });

    console.log("New state DIRECTLY after setState:", this.state.co2level1);
    console.log("New state DIRECTLY after setState:", this.state.co2level1);



  }
  updatesmokeValueSensor1 = event => {
    event.preventDefault()
    console.log(event.target.value)
    var val = event.target.value
    console.log(val + "this is the value retrived")
    this.setState({
      smokelevel1: parseInt(event.target.value)
    }, () => {
      console.log("New state in ASYNC callback:", this.state.smokelevel1);
    });

    console.log("New state DIRECTLY after setState:", this.state.smokelevel1);



  }
  //sensor 2---------------------------------------------------------------------------

  updateco2ValueSensor2 = event => {
    event.preventDefault()
    console.log(event.target.value)
    var val = event.target.value
    console.log(val + "this is the value retrived")
    this.setState({
      co2level2: parseInt(event.target.value)
    }, () => {
      console.log("New state in ASYNC callback:", this.state.co2level2);
    });

    console.log("New state DIRECTLY after setState:", this.state.co2level2);
    console.log("New state DIRECTLY after setState:", this.state.co2level2);



  }
  updatesmokeValueSensor2 = event => {
    event.preventDefault()
    console.log(event.target.value)
    var val = event.target.value
    console.log(val + "this is the value retrived")
    this.setState({
      smokelevel2: parseInt(event.target.value)
    }, () => {
      console.log("New state in ASYNC callback:", this.state.smokelevel2);
    });

    console.log("New state DIRECTLY after setState:", this.state.smokelevel2);
  }

  updateid = event => {
    event.preventDefault()
    console.log(event.target.value)
    var val = event.target.value
    console.log(val + "this is the value retrived")
    this.setState({
      id: parseInt(event.target.value)
    }, () => {
      console.log("New state in ASYNC callback:", this.state.id);
    });

    console.log("New state DIRECTLY after setState:", this.state.id);
  }
  updatesenid = event => {
    event.preventDefault()
    console.log(event.target.value)
    var val = event.target.value
    console.log(val + "this is the value retrived")
    this.setState({
      senid: parseInt(event.target.value)
    }, () => {
      console.log("New state in ASYNC callback:", this.state.senid);
    });

    console.log("New state DIRECTLY after setState:", this.state.senid);
  }


  //sensor0 api call---------------------------------------------------------------------------

  updateapi0 = (event) => {

    event.preventDefault();
    // alert("successfully added")
    let sensor0body = JSON.stringify(
      {
        "co2Lvl": this.state.co2level0,
        "smokeLvl": this.state.smokelevel0,
        "id": 9,
        "sensorId": 0,

      }
    );

    axios({
      headers: {

        // 'Content-Type ': 'application/x-www-form-urlencoded;charset=UTF-8'
        'Content-Type': 'application/json;charset=UTF-8',
      },



      method: 'PUT',
      url: 'http://localhost:8089/updateSensorApp',
      data: sensor0body,

    })
      .then(response => {
        console.log("Arrived to send request")
        this.setState({
          responseSensor0: response.data
        })
      })
      .then(() => {
        alert('Successfully updated !')
      })

      .catch((console.log("ISSUES !")))

  }
  //sensor1 api call---------------------------------------------------------------------------
  updateapi1 = (event) => {

    event.preventDefault();
    // alert("successfully added")
    let sensor0body1 = JSON.stringify(
      {
        "co2Lvl": this.state.co2level1,
        "smokeLvl": this.state.smokelevel1,
        "id": 10,
        "sensorId": 2,

      }
    );

    axios({
      headers: {
        // 'Content-Type ': 'application/x-www-form-urlencoded;charset=UTF-8'
        'Content-Type': 'application/json;charset=UTF-8',
      },



      method: 'PUT',
      url: 'http://localhost:8089/updateSensorApp',
      data: sensor0body1,

    })
      .then(response => {
        console.log("Arrived to send request")
        this.setState({
          responseSensor1: response.data
        })
      })
      .then(() => {
        alert('Successfully updated !')
      })

      .catch((console.log("ISSUES !")))

  }


  //sensor2 api call---------------------------------------------------------------------------
  updateapi2 = (event) => {

    event.preventDefault();
    // alert("successfully added")
    let sensor0body2 = JSON.stringify(
      {
        "co2Lvl": this.state.co2level2,
        "smokeLvl": this.state.smokelevel2,
        "id": this.state.id,
        "sensorId": this.state.senid,

      }
    );

    axios({
      headers: {

        // 'Content-Type ': 'application/x-www-form-urlencoded;charset=UTF-8'
        'Content-Type': 'application/json;charset=UTF-8',
      },



      method: 'PUT',
      url: 'http://localhost:8089/updateSensorApp',
      data: sensor0body2,

    })
      .then(response => {
        console.log("Arrived to send request")
        this.setState({
          responseSensor2: response.data
        })
      })
      .then(() => {
        alert('Successfully updated !')
      })

      .catch((console.log("ISSUES !")))

  }



  render() {
    return (
      <div style={{ minHeight: '100vh', textAlign: 'center', backgroundColor: '#254259', padding: 20, justifyContent: 'center', alignItems: 'center' }}>
        <div style={{ marginTop: 150, backgroundColor: '#ffffff', marginLeft: 300, marginRight: 300, borderRadius: 10 }}>
          <h1 style={{ marginTop: 50, color: '#1f3a5c' }}>DUMMY SENSORS <MdWifi size={50} style={{ color: '#1f3a5c' }} /></h1>
        </div>


        <header style={{ display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center' }}>

          <div style={{ backgroundColor: '#ffffff', height: 500, width: 400, borderRadius: 30, border: '2px solid blue' }} >
            <div style={{ fontSize: 25, marginTop: 10, color: '#1f3a5c', fontWeight: 'bold', }}>
              SENSOR 0
          </div>
            <MdWifi size={50} style={{ color: '#1f3a5c' }} />
            <div style={{ flexDirection: 'column', marginTop: 50 }}>
              <form >
                <input type="text" placeholder="                Co2 level" name="co2level0" onChange={this.updateco2ValueSensor0}
                  style={{ height: 60, width: 250, borderRadius: 30, fontSize: 20, alignItems: 'center', fontWeight: 'bold', }}

                />
                <br />
                <input type="text" name="smokelevel0" onChange={this.updatesmokeValueSensor0}
                  style={{ height: 60, width: 250, borderRadius: 30, marginTop: 40, fontSize: 20, alignItems: 'center', fontWeight: 'bold', }}
                  placeholder="              Smoke level"
                />
                <br />
                <button onClick={this.updateapi0} style={{
                  height: 50, width: 200, borderRadius: 30, backgroundColor: '#1f3a5c', color: '#ffffff', fontSize: 20, fontWeight: 'bold', marginTop: 30
                }}>
                  Update details
                  </button>
              </form>
            </div>
          </div>


          <div style={{ backgroundColor: '#ffffff', height: 500, width: 400, borderRadius: 30, border: '2px solid blue', marginLeft: 20 }} >
            <div style={{ fontSize: 25, fontWeight: 'bold', marginTop: 10, color: '#1f3a5c' }}>
              SENSOR 2
          </div>
            <MdWifi size={50} style={{ color: '#1f3a5c' }} />
            <div style={{ flexDirection: 'column', marginTop: 50 }}>
              <form>
                <input type="text" style={{ height: 60, width: 250, borderRadius: 30, fontSize: 20, alignItems: 'center', fontWeight: 'bold', }} onChange={this.updateco2ValueSensor1}
                  placeholder="                Co2 level"

                />
                <br />
                <input type="text" style={{ height: 60, width: 250, borderRadius: 30, marginTop: 40, fontSize: 20, alignItems: 'center', fontWeight: 'bold', }} onChange={this.updatesmokeValueSensor1}
                  placeholder="              Smoke level"

                />
                <button onClick={this.updateapi1} style={{
                  height: 50, width: 200, borderRadius: 30, backgroundColor: '#1f3a5c', color: '#ffffff', fontSize: 20, fontWeight: 'bold', marginTop: 30
                }}>
                  Update details
                  </button>
              </form>
            </div>
          </div>


          <div style={{ backgroundColor: '#1f3a5c', height: 500, width: 400, borderRadius: 30, border: '2px solid white', marginLeft: 20 }} >
            <div style={{ fontSize: 25, fontWeight: 'bold', marginTop: 10, color: '#ffffff' }}>
              ANY SENSOR
          </div>
            <MdWifi size={50} style={{ color: '#ffffff' }} />
            <div style={{ flexDirection: 'column', marginTop: 30 }}>
              <form>
                <input type="text" style={{ height: 40, width: 250, borderRadius: 30, fontSize: 20, alignItems: 'center', fontWeight: 'bold', }} onChange={this.updateid}
                  placeholder="                     ID"

                />
                <input type="text" style={{ height: 40, width: 250, borderRadius: 30, fontSize: 20, alignItems: 'center', fontWeight: 'bold', marginTop: 10 }} onChange={this.updatesenid}
                  placeholder="               Sensor ID"

                />
                <input type="text" style={{ height: 40, width: 250, borderRadius: 30, fontSize: 20, alignItems: 'center', fontWeight: 'bold', marginTop: 10 }} onChange={this.updateco2ValueSensor2}
                  placeholder="               Co2 level"

                />
                <br />
                <input type="text" style={{ height: 40, width: 250, borderRadius: 30, marginTop: 10, fontSize: 20, alignItems: 'center', fontWeight: 'bold', }} onChange={this.updatesmokeValueSensor2}
                  placeholder="             Smoke level"

                />
                <button onClick={this.updateapi2} style={{
                  height: 50, width: 200, borderRadius: 30, backgroundColor: '#ffffff', color: '#1f3a5c', fontSize: 20, fontWeight: 'bold', marginTop: 30
                }}>
                  Update details
                  </button>
              </form>
            </div>
          </div>
        </header>
      </div>

    );
  }

}

