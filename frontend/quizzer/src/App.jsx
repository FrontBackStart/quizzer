import QuizList from './components/QuizList';
import Navbar from './components/Navbar'
import CategoryList from './components/CategoryList';
import QuestionList from './components/QuestionList';
import Container from '@mui/material/Container'
import { Route, Routes } from 'react-router-dom';
import CategoryIdList from './components/CategoryIdList';


function App() {
	return (
		<Container maxWidth="xl">
			<Navbar />
			<Routes>
				<Route path="/" element={<QuizList />} />
				<Route path="/quizzes" element={<QuizList />} />
				<Route path="/categories" element={<CategoryList />} />
				<Route path="/questions/:quizId" element={<QuestionList />} />
				<Route path="/categories/:categoryId" element={<CategoryIdList />} />
			</Routes>
		</Container>
	)
}

export default App
