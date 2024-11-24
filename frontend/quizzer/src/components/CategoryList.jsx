import { useState, useEffect } from "react";
import Typography from '@mui/material/Typography';
import { AgGridReact } from 'ag-grid-react';
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";


function CategoryList() {
	const [categories, setCategories] = useState([]);

	const gridOptions = {
		autoSizeStrategy: {
			type: 'fitGridWidth',
		},
		columnDefs: [
			{ field: "name", headerName: "Name", width: 100 },
			{ field: "description", headerName: "Description" }
		]
	}

	async function getCategories() {
		const response = await fetch("http://localhost:8080/api/categories");
		setCategories(await response.json());
	};

	useEffect(() => {
		getCategories();
	}, []);

	return (
		<>
			<Typography variant="h4">Categories</Typography>
			<div className='ag-theme-material' style={{ height: 500 }}>
				<AgGridReact
					rowData={categories}
					gridOptions={gridOptions}
					suppressCellFocus={true}
				/>
			</div>
		</>
	)
}

export default CategoryList;
