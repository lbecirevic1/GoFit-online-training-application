import React from "react";
import { useState } from "react";

import { useEffect } from "react";

import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import timeGridPlugin from "@fullcalendar/timegrid";
import resourceTimeGridPlugin from "@fullcalendar/resource-timegrid";
import Modal from "../Modal";
import ScheduleService from "../../services/ScheduleService";

const CalendarComponent = () => {
  const[editModal,setEditModal] = useState(false)
  const [events, setEvents] = useState([]);
  const [modal, setModal] = useState(false);
  const [selectedDate, setSelectedDate] = useState({
    startDate: new Date(),
    endDate: new Date(),
  });
  const [wsel,setWsel] = useState({})

  const [selectedWorkouts, setSelectedWorkouts] = useState([]);
  const [error, setError] = useState({ isError: false, message: "" });
  const [data, setData] = useState({
    id:"",
    title: "",
    description: "",
    start: "",
    end: "",
    trainings: [],
  });

  useEffect(() => {
    data.start = selectedDate.startDate;
    data.end = selectedDate.endDate;
    setData({...data})
  }, [selectedDate]);

  useEffect(() => {
    setData({
      title: "",
      note: "",
      start: "",
      end: "",
      trainings: [],
    });
    setSelectedWorkouts([])
  }, [events]);

  useEffect(() => {
    ScheduleService.getWorkouts().then((result) =>{
      result.map((w)=> {
      w.start= new Date(w.start)
      w.end= new Date(w.end)
      return w
      })
      setEvents(result)
    })
  }, []);

  // useEffect(() => {
  //   ScheduleService.getWorkouts().then((result) =>{
  //     result.map((w)=> {
  //     w.start= new Date(w.start)
  //     w.end= new Date(w.end)
  //     return w
  //     })
  //     setEvents(result)
  //   })
  // }, [modal]);

  const openModal = () => {
    setModal(!modal);
  };

  const addToSelectedItems = (workout) => {
    console.log('selectedWorkouts',selectedWorkouts)
    const alreadySelected = selectedWorkouts.find(
      (item) => item.id === workout.id
    );
    if (!alreadySelected) {
      setSelectedWorkouts([...selectedWorkouts, workout]);
    } else {
      setSelectedWorkouts([
        ...selectedWorkouts.filter((item) => item.id !== workout.id),
      ]);
    }
  };

  const createWorkout = async () => {
    if (selectedWorkouts.length === 0) {
      setError({ isError: true, message: "You have to select workout" });
    } else {
      data.trainings = (selectedWorkouts || []).map(item =>item.id);
      setData({ ...data });
    }
    if (data.title.length === 0) {
      setError({ isError: true, message: "Plase add workout name" });
    }
    if (!error.isError) {
      setEvents([...events, data]);
      setSelectedWorkouts([])
      const res = await ScheduleService.addNewWorkout(data)
      if(res.status === 201) {
        openModal();
      }
    } else {
     
    }
  };

  const updateData = (value, fieldName) => {
    data[fieldName] = value;
    setData({ ...data });
  };

  const handleDateClick = (arg) => {
    setEditModal(false)
    openModal();
  };

  const handleSelectedDates = (info) => {
    setSelectedDate({
      startDate: new Date(info.startStr),
      endDate: new Date(info.endStr),
    });

    ScheduleService.getWorkouts().then(result=> {
      result.map(w=> {
        w.start = new Date(w.start)
        w.end = new Date(w.end)
        return w
      })
      setEvents(result)
    })
  };

  const handleOpenEvent = (selectedEvent) =>{
    setEditModal(true);
    setWsel(selectedEvent)
   // setData({...selectedEvent})
    console.log('selectedEvent',selectedEvent)
    setSelectedDate({
      startDate: selectedEvent.start,
      endDate: selectedEvent.end
    })
  
    setSelectedWorkouts(selectedEvent?.trainings?.split(',').map(tr=>{return {id: tr}}))
    openModal()
  }

  const calendarComponentRef = React.createRef();
  return (
    <div className="container py-5">
      <Modal
      
      wsel={wsel}
      editModal={editModal}
        data={data}
        isOpen={modal}
        toggle={openModal}
        updateData={updateData}
        startDate={selectedDate.startDate}
        endDate={selectedDate.endDate}
        createWorkout={createWorkout}
        error={error}
        setError={setError}
        selectedWorkouts={selectedWorkouts}
        addToSelectedItems={addToSelectedItems}
      />

      <FullCalendar
        schedulerLicenseKey="GPL-My-Project-Is-Open-Source"
        ref={calendarComponentRef}
        defaultView="dayGridMonth"
        dateClick={handleDateClick}
        displayEventTime={true}
        header={{
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay,listWeek",
        }}
        selectable={true}
        plugins={[
          dayGridPlugin,
          interactionPlugin,
          timeGridPlugin,
          resourceTimeGridPlugin,
        ]}
        eventClick={(event) => {
          console.log(event.event._def.publicId);
          const selectedItem = {
            id: parseInt(event.event._def.publicId),
            title: event.event._def.title,
            start:event.event._instance.range.start,
            end:event.event._instance.range.end,
            ...event.event._def.extendedProps
          }
          handleOpenEvent(selectedItem)
          setData({...selectedItem})
          console.log('selectedItem',selectedItem)
        }}
        events={events}
        select={handleSelectedDates}
        eventLimit={3}
      />
    </div>
  );
};

export default CalendarComponent;
