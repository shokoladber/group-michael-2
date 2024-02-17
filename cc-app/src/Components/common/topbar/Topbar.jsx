import React from 'react';
import './Topbar.css';

function Topbar() {
    return (
        <div className='top'>
            <div className='topLeft'></div>
            <div className='topCenter'>
                <ul className="topList">
                    {/* Add list items here if needed */}
                </ul>
            </div>
            <div className='topRight'>
                {/* You can add content to the right side here */}
            </div>
        </div>
    );
}

export default Topbar;
