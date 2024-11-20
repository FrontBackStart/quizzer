import QuizList from './components/QuizList';
import Container from '@mui/material/Container'
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';

function App() {
	return (
		<Container maxWidth="xl">
			<AppBar position='static'>
				<Toolbar>
					<Typography variant="h6">Quizzer</Typography>
				</Toolbar>
			</AppBar>
			<QuizList />	
		</Container>
	)
}

export default App
