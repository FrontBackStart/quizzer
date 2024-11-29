import { useState, useEffect } from "react";
import { AgGridReact } from 'ag-grid-react';
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";
import Typography from '@mui/material/Typography';
import { useParams } from "react-router-dom";


function ResultsList() {
    const [results, setResults] = useState([]);
    const [questions, setQuestions] = useState([]);
    const { quizId } = useParams();


    const gridOptions = {
		autoSizeStrategy: {
			type: 'fitGridWidth',
		},
        columnDefs: [
            { field: "questionText", headerName: "Question" },
            { field: "difficulty", headerName: "Difficulty" },
            { field: "totalAnswers", headerName: "Total answers" },
            { 
                headerName: "Correct answer %",
                valueGetter: (p) => (p.data.totalRightAnswers / p.data.totalAnswers) * 100 + "%"

            },
            { field: "totalRightAnswers", headerName: "Correct answers" },
            { 
                headerName: "Wrong answers",
                valueGetter: (p) => p.data.totalAnswers - p.data.totalRightAnswers
            },
        ]
    }

    async function getResults() {
        try {
            const response = await fetch(`http://localhost:8080/api/seeresults/${quizId}`);
            const data = await response.json();

            setResults({
                totalAnswers: data.totalAnswers,
                totalRightAnswers: data.totalRightAnswers,
                quizId: data.quizId,
                quizName: data.quizName
            });
            setQuestions(data.questions);
        } catch (error) {
            console.error("Error fetching results data:", error);
        }
    }

    useEffect(() => {
        getResults(); 
    }, [quizId]);

    return (
    <>
    <Typography sx={{ pt: 1.5, pl: 1 }} variant="h4">{results.quizName}</Typography>
        <div className='ag-theme-material' style={{ height: 500 }}>
            <AgGridReact
                rowData={questions}
                gridOptions={gridOptions}
                suppressCellFocus={true}
            />
        </div>
    </>
    )
}

export default ResultsList
