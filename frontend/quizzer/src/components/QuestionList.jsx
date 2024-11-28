import { useState, useEffect } from "react";
import { AgGridReact } from 'ag-grid-react';
import { useParams } from "react-router-dom";
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { Card, FormControl, FormControlLabel, Radio, RadioGroup } from "@mui/material";

function QuestionList() {
    const [questions, setQuestions] = useState([]);
    const { quizId } = useParams();
    const [quizDetails, setQuizDetails] = useState(null);
    const [selectedAnswers, setSelectedAnswers] = useState({}); // Track selected answers

    async function fetchQuizData() {
        try {
            const response = await fetch(`http://localhost:8080/api/quizzes/${quizId}`);
            const data = await response.json();

            setQuizDetails({
                name: data.name,
                description: data.description,
                created: data.created,
                questionCount: data.questionCount,
                category: data.category,
            });
            setQuestions(data.questions);
        } catch (error) {
            console.error("Error fetching quiz data:", error);
        }
    }

    useEffect(() => {
        fetchQuizData();
    }, [quizId]);

    const handleAnswerChange = (questionId, answerId) => {
        setSelectedAnswers((prev) => ({
            ...prev,
            [questionId]: answerId,
        }));
    };

    const handleSubmitAnswer = async (questionId) => {
        const selectedAnswerId = selectedAnswers[questionId];
        if (!selectedAnswerId) {
            alert("Please select an answer before submitting.");
            return;
        } else {
            alert(selectedAnswerId);
        }
    };

    return (
        <>
            <Typography variant="h4" gutterBottom>
                {quizDetails?.name || "Loading..."}
            </Typography>
            <div>
                {quizDetails && (
                    <>
                        <div>{quizDetails.description}</div>
                        <div>
                            Added on: {quizDetails.created ? new Date(quizDetails.created).toLocaleDateString('de-DE') : "Loading..."} - 
                            Questions: {quizDetails.questionCount} - 
                            Category: {quizDetails?.category || "Loading..."}
                        </div>
                    </>
                )}
            </div>
            <div>
            {questions.map((question, index) => (
                    <Card key={index} style={{ marginBottom: 10, padding: 20 }}>
                        <Typography variant="h6">{question.questionText}</Typography>
                        <Typography variant="body2">
                            Question {index + 1} of {quizDetails?.questionCount} - Difficulty:{" "}
                            {question.difficulty || "Unknown"}
                        </Typography>
                        <FormControl component="fieldset">
                            <RadioGroup
                                value={selectedAnswers[question.questionId] || ""}
                                onChange={(e) =>
                                    handleAnswerChange(question.questionId, e.target.value)
                                }
                            >
                                {question.answers.map((answer) => (
                                    <FormControlLabel
                                        key={answer.answerId}
                                        value={answer.answerId}
                                        control={<Radio />}
                                        label={answer.answerText}
                                    />
                                ))}
                            </RadioGroup>
                        </FormControl>
                        <br />
                        <button
                            style={{
                                backgroundColor: "transparent",
                                color: "blue",
                                border: "none",
                                cursor: "pointer",
                            }}
                            onClick={() => handleSubmitAnswer(question.questionId)}
                        >
                            SUBMIT YOUR ANSWER
                        </button>
                    </Card>
                ))}
            </div>
        </>
    );
}

export default QuestionList;
