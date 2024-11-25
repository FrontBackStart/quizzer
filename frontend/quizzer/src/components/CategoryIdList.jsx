import { useState, useEffect } from "react";
import { AgGridReact } from 'ag-grid-react';
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";
import Typography from '@mui/material/Typography';
import { useParams } from "react-router-dom";


function CategoryIdList() {
	const [quizzes, setQuizzes] = useState([]);
    const { categoryId } = useParams();

    const gridOptions = {
		autoSizeStrategy: {
			type: 'fitGridWidth',
		},
        columnDefs: [
            { field: "name", headerName: "Quiz Name" },
            { field: "description", headerName: "Description" },
            { field: "created", headerName: "Created Date" },
        ]
    }

    async function fetchQuizData() {
        const response = await fetch(`http://localhost:8080/api/categories/${categoryId}`);
        const data = await response.json();
        setQuizzes(data);
    }

    useEffect(() => {
        fetchQuizData(); 
    }, [categoryId]);

    return (
    <>
        <div className='ag-theme-material' style={{ height: 500 }}>
            <AgGridReact
                rowData={quizzes}
                gridOptions={gridOptions}
                suppressCellFocus={true}
            />
        </div>
    </>
    )
}

export default CategoryIdList;
