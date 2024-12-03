import react from "react";
import { NavLink, useLocation } from "react-router-dom";
import ApiService from "./ApiService";

export const protectedRoute = ({element: Component}) => {
    const location = useLocation();

    return ApiService.isAthenticated() ?(
        Component
    ):(
        <navigate to="/login" replace state={{from: location}} />
    );
};

