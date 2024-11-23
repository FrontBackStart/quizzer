import QuizList from './components/QuizList';
import Navbar from './components/Navbar'
import CategoryList from './components/CategoryList';
import QuestionList from './components/QuestionList';
import Container from '@mui/material/Container'
import { Route, Routes } from 'react-router-dom';


function App() {
	return (
		<Container maxWidth="xl">
			<Navbar />
			<Routes>
				<Route path="/" element={<QuizList />} />
				<Route path="/quizzes" element={<QuizList />} />
				<Route path="/categories" element={<CategoryList />} />
				<Route path="/questions/:quizId" element={<QuestionList />} />
			</Routes>
		</Container>
	)
}

export default App
