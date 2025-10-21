import React, { useMemo, useState } from 'react'
import './Dashboard.css'

function startOfWeek(date) {
  const d = new Date(date)
  const day = d.getDay() === 0 ? 6 : d.getDay() - 1 // Monday start
  d.setDate(d.getDate() - day)
  d.setHours(0, 0, 0, 0)
  return d
}

function addDays(date, days) {
  const d = new Date(date)
  d.setDate(d.getDate() + days)
  return d
}

// Temporary demo data — will be replaced with backend later
const demoAssignments = [
  { id: 1, title: 'Math Homework', course: 'Calculus', dueAt: '2025-10-21T17:00:00', status: 'todo', priority: 'high' },
  { id: 2, title: 'Essay Outline', course: 'English', dueAt: '2025-10-23T09:00:00', status: 'in-progress', priority: 'medium' },
  { id: 3, title: 'Lab Report', course: 'Biology', dueAt: '2025-10-25T12:00:00', status: 'done', priority: 'low' },
]

function Dashboard() {
  const [weekAnchor, setWeekAnchor] = useState(new Date())

  // Generate this week's 7 days (Mon–Sun)
  const days = useMemo(
    () => Array.from({ length: 7 }, (_, i) => addDays(startOfWeek(weekAnchor), i)),
    [weekAnchor]
  )

  return (
    <div className="container">
      <div className="header">
        <div className="text-sdsu">📚 SDSU Assignments Tracker</div>
        <div className="text">Weekly Dashboard</div>
        <div className="underline"></div>
      </div>

      <div className="week">
        {days.map(day => {
          const dateStr = day.toDateString()
          const assignments = demoAssignments.filter(
            a => new Date(a.dueAt).toDateString() === dateStr
          )
          return (
            <div key={dateStr} className="day-column">
              <div className="day-header">
                <div className="day-title">
                  {day.toLocaleDateString(undefined, { weekday: 'short', day: 'numeric' })}
                </div>
              </div>
              <div className="day-body">
                {assignments.length === 0 ? (
                  <div className="empty">No assignments</div>
                ) : (
                  assignments.map(a => (
                    <div key={a.id} className={`assignment-card ${a.status}`}>
                      <div className="title">{a.title}</div>
                      <div className="course">{a.course}</div>
                      <div className="priority">{a.priority.toUpperCase()}</div>
                    </div>
                  ))
                )}
              </div>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default Dashboard
