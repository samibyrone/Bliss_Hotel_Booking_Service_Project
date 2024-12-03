import React from "react";
import { Navigate , useLocation } from "react-router-dom";
import ApiService from "./ApiService";


export const ProtectedRoute = ({element: Component}) => {
    const location = useLocation();

    return ApiService.isAthenticated() ?(
        Component
    ):(
        <Navigate to="/login" replace state={{from: location}} />
    );
};

export const AdminRoute = ({element: Component}) => {
    const location = useLocation();

    return ApiService.isAdmin() ?(
        Component
    ):(
        <navigate to="/login" replace state={{from: location}} />
    );
};
