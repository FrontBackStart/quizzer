import { useState, useEffect } from "react";
import { AgGridReact } from 'ag-grid-react';
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";

function QuizList() {
    const [quizzes, setQuizzes] = useState([]);

    const [colDefs, setColDefs] = useState([
        { field: "name" },
        { field: "description" },
        { field: "category.name", headerName: "Category" },
        { field: "created" },
    ])

    async function getQuizzes() {
        const response = await fetch("http://localhost:8080/api/quizzes");
        setQuizzes(await response.json());
    };

    useEffect(() => {
        getQuizzes();
    }, []);

    return (
        <div className='ag-theme-material' style={{ height: 500 }}>
            <AgGridReact
                rowData={quizzes}
                columnDefs={colDefs}
                suppressCellFocus={true}
            />
        </div>
    )
}

export default QuizList