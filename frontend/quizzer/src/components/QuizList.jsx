import { useState, useEffect } from "react";
import { AgGridReact } from 'ag-grid-react';
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";
import Typography from '@mui/material/Typography';
import { useNavigate } from "react-router-dom";
import { getAllQuizzes } from "../services/fetches";

function QuizList() {
    const [quizzes, setQuizzes] = useState([]);
    const navigate = useNavigate();

    const gridOptions = {
		autoSizeStrategy: {
			type: 'fitGridWidth',
		},
        columnDefs: [
            {
                field: "name",
                headerName: "Quiz Name",
                cellRenderer: (params) => {
                    const quizId = params.data.quizId; // Assuming quizId exists in the data
                    return (
                        <button
                            style={{
                                backgroundColor: "transparent",
                                color: "blue",
                                border: "none",
                                textDecoration: "underline",
                                cursor: "pointer"
                            }}
                            onClick={() => navigate(`/questions/${quizId}`)}
                        >
                            {params.value}
                        </button>
                    );
                },
            },
            { field: "description", headerName: "Description" },
            { field: "category.name", headerName: "Category" },
            {
                field: "created",
                headerName: "Created Date",
                valueFormatter: (params) => {
                    const date = new Date(params.value);
                    return date.toLocaleDateString('de-DE');
                },
            },
            { field: "actions",
            headerName: "Actions",
            cellRenderer: (params) => {
                const quizId = params.data.quizId;
                return (
                    <button
                        style={{
                            backgroundColor: "transparent",
                            color: "blue",
                            border: "none",
                            textDecoration: "underline",
                            cursor: "pointer"
                        }}
                        onClick={() => navigate(`/quizzes/${quizId}/reviews`)}
                    >
                        See Reviews
                        </button>
                    );
                },
            }
        ]
    }

    function getQuizzes() {
        getAllQuizzes().then((quizzes) => {
            setQuizzes(quizzes);
    });
    }

    useEffect(() => {
        getQuizzes();
    }, []);

    return (
    <>
    <Typography sx={{ pt: 1.5, pl: 1 }} variant="h4">Quizzes</Typography>
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

export default QuizList
