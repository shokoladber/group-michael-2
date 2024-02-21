import React from 'react'
import './CourseList';

function CourseList() {
  return (
    <div className='course-form'>
            <div className='four-group'>
                <h1> 1.Class</h1>
                    <ul>
                        <li>Puppy</li>
                        <li>Beginner</li>
                        <li>Intermediate</li>
                        <li>Reactive</li>
                    </ul>
            </div>

            <div>
                <h1>2. Agility</h1>
                    <ul>
                        <li>Beginner Agility</li>
                        <li>Open Agility</li>
                    </ul>
            </div>

            <div>
                <h1>3. Private</h1>
                    <ul>
                        <li>Facility</li>
                        <li>In-Home</li>
                    </ul>
            </div>

            <div>
                <h1>4. Social</h1>
                    <ul>
                        <li>Puppy Play</li>
                        <li>Teen Play</li>
                        <li>Adult Play</li>
                    </ul>
            </div>

    </div>
  )
}

export default CourseList