import React from 'react';
import { FaUser } from "react-icons/fa";
import { MdEmail } from "react-icons/md";
import { RiLockPasswordFill } from "react-icons/ri";

const AuthForm = ({ action, onSubmit, onChange, values }) => {
    return (
        <form onSubmit={onSubmit}>
            {action === "Signup" && (
                <div className="input">
                    <FaUser />
                    <input
                        type="text"
                        placeholder="Name"
                        name="name"
                        value={values.name}
                        onChange={onChange}
                    />
                </div>
            )}
            <div className="input">
                <MdEmail />
                <input
                    type="email"
                    placeholder="Email"
                    name="email"
                    value={values.email}
                    onChange={onChange}
                />
            </div>
            <div className="input">
                <RiLockPasswordFill />
                <input
                    type="password"
                    placeholder="Password"
                    name="password"
                    value={values.password}
                    onChange={onChange}
                />
            </div>
            <button type="submit">{action}</button>
        </form>
    );
};

export default AuthForm;
