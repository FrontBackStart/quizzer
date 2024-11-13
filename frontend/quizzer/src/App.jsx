import { useState, useEffect } from "react";
import "./App.css"

function App() {
	const [quizzes, setQuizzes] = useState([]);

	async function getQuizzes() {
		const response = await fetch("http://localhost:8080/api/quizzes");
		setQuizzes(await response.json());
	};

	useEffect( () => {
		getQuizzes();
	}, []);

  return (
    <div>
    	<ol>
				{quizzes.map((quiz) => {
					return (
						<li key={quiz.quizId}>{quiz.name}</li>
					)
				})}
     </ol>
    </div>
  )
}

export default App
