import { useState, useEffect } from "react";
import { AgGridReact } from 'ag-grid-react';
import { useParams } from "react-router-dom";
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { Card } from "@mui/material";

function QuestionList() {
    const [questions, setQuestions] = useState([]);
    const { quizId } = useParams();
    const [quizDetails, setQuizDetails] = useState(null);

    async function fetchQuizData() {
        const response = await fetch(`http://localhost:8080/api/quizzes/${quizId}`);
        const data = await response.json();
        setQuizDetails(data);
        setQuestions(data.questions); 
    }

    

    useEffect(() => {
        fetchQuizData(); 
    }, [quizId]); 

    return (
        <>
            <Typography variant="h4" gutterBottom>
                {quizDetails?.name || "Loading..."}
            </Typography>
            <div>
                {quizDetails && (
                    <>
                        <div>{quizDetails.description}</div>
                        <div>Added on: - Questions: {quizDetails.questionCount} - Category: {quizDetails?.category || "Loading..."}</div>
                    </>
                )}
            </div>
            <div>
                {questions.map((question, index) => (
                    <Card key={index} style={{ marginBottom: 10, padding: 20 }}>
                        <Typography variant="h6">{question.questionText}</Typography>
                        <Typography variant="body2"> 
                            Question {index + 1} of {quizDetails?.questionCount} - Difficulty: {question.difficulty}
                        </Typography>
                    </Card>
                ))}
            </div>
        </>
    );
}

export default QuestionList;