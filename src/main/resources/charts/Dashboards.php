<?php
	$con = mysqli_connect('localhost', 'newuser', 'URJC2019!', 'practica3', '3306');
?>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Dashboards Practica 3</title>

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

	<style>
		.bg-general {
			background: #eee;
		}

		.title-chart {
		    text-align: center;
		    font-size: 15px;
		    font-family: monospace;
		    font-weight: bold;
		}

		.header-table {
			background: #937777;
			color: black;
		}
	</style>

	<script type="text/javascript">
		google.charts.load("current", {packages:["table"]});
		google.setOnLoadCallback(drawTable);

		function drawTable() {
			var data = google.visualization.arrayToDataTable([
				['Hospital', 'Pacientes', 'Mujeres', 'Hombres'],
				<?php
					$query = "SELECT a.id_hospital as `Hospital`,
                              		COUNT(a.id_paciente) as `Pacientes`,
                                    SUM(b.sexo = 'M') as `Mujeres`,
                                    SUM(b.sexo = 'H') as `Hombres`
                              FROM `tabla_hechos` a, `dim_paciente` b
                              WHERE (a.id_paciente = b.id)
                              GROUP BY a.id_hospital ";
					$exec = mysqli_query($con, $query);
					while($row = mysqli_fetch_array($exec)) {
						echo "['".$row['Hospital']."',".$row['Pacientes'].",".$row['Mujeres'].",".$row['Hombres']."],";
					}
				?>
			]);

			var table = new google.visualization.Table(document.getElementById('table_div'));

			table.draw(data, {
				showRowNumber: true, 
				width: '100%', 
				height: '100%',
				cssClassNames: {
					headerRow: 'header-table'
				},
				sortColumn: 1
			});
		}

		google.charts.load('current', {'packages':['corechart']});
		google.setOnLoadCallback(drawBarChartUCI);

		function drawBarChartUCI() {
			var data = google.visualization.arrayToDataTable([
				['Hospital', 'PacientesUCI'],
				<?php
					$query = "SELECT a.id_hospital as `Hospital`,
								COUNT(*) as `PacientesUCI`
								FROM `tabla_hechos` a
                                WHERE (a.uci = 1)
                                GROUP BY a.id_hospital";
					$exec = mysqli_query($con, $query);
					while($row = mysqli_fetch_array($exec)) {
						echo "['".$row['Hospital']."',".$row['PacientesUCI']."],";
					}
				?>
				]);

			var options = {
				annotations: {
					alwaysOutside: true
				},
				legend: {position: 'bottom'},
				colors: ['#4C3B4D']
			};

			var chart = new google.visualization.BarChart(document.getElementById("BarChartUCI"));

			chart.draw(data, options);
		}

		google.charts.load('current', {'packages':['corechart']});
		google.setOnLoadCallback(drawBarChartDeaths);

		function drawBarChartDeaths() {
			var data = google.visualization.arrayToDataTable([
				['Hospital', 'PacientesFallecidos'],
				<?php
					$query = "SELECT a.id_hospital as `Hospital`,
								COUNT(*) as `PacientesFallecidos`
								FROM `tabla_hechos` a
                                WHERE (a.fallecido = 1)
                                GROUP BY a.id_hospital";
					$exec = mysqli_query($con, $query);
					while($row = mysqli_fetch_array($exec)) {
						echo "['".$row['Hospital']."',".$row['PacientesFallecidos']."],";
					}
				?>
				]);

			var options = {
				annotations: {
					alwaysOutside: true
				},
				legend: {position: 'bottom'},
				colors: ['#55DBCB']
			};

			var chart = new google.visualization.BarChart(document.getElementById("BarChartFallecido"));

			chart.draw(data, options);
		}

		google.charts.load("current", {packages:["corechart"]});
		google.setOnLoadCallback(drawHistogramChart);

		function drawHistogramChart() {
			var data = google.visualization.arrayToDataTable([
				['Paciente', 'Edad'],
				<?php
					$query = "SELECT DISTINCT a.id_paciente as `Paciente`,
							b.edad as `Edad`
							FROM `tabla_hechos` a, `dim_paciente` b
							WHERE (a.id_paciente = b.id) AND (a.uci = 1)";
					$exec = mysqli_query($con, $query);
					while($row = mysqli_fetch_array($exec)) {
						echo "['".$row['Paciente']."',".$row['Edad']."],";
					}
				?>
				]);

			var options = {
				legend: {position: 'bottom'},
				colors: ['#CEBEBE']
			};

			var chart = new google.visualization.Histogram(document.getElementById('HistogramChart'));

			chart.draw(data, options);
		}

		google.charts.load('current', {'packages':['corechart']});
		google.setOnLoadCallback(drawPieChart);

		function drawPieChart() {
			var data = google.visualization.arrayToDataTable([
				['Enfermedad', 'Numero'],
				<?php
					$query = "SELECT 'Alguna enfermedad' as `Enfermedad`,
									SUM(b.alcoholismo = 1 OR b.cancer = 1 OR 
                                    b.cardiopatia = 1 OR b.colesterol = 1 OR
                                    b.epoc = 1 OR b.hipertension = 1 OR
                                    b.reuma = 1 OR b.tabaquismo = 1 OR
                                    b.imc > 30) as `Numero`
								FROM `tabla_hechos` a, `dim_paciente` b
                                WHERE (a.uci = 1) AND (a.id_paciente = b.id)
								UNION                                
								SELECT 'Ninguna enfermedad' as `Enfermedad`,
									SUM(b.alcoholismo = 0 AND b.cancer = 0 AND 
                                    b.cardiopatia = 0 AND b.colesterol = 0 AND
                                    b.epoc = 0 AND b.hipertension = 0 AND
                                    b.reuma = 0 AND b.tabaquismo = 0 AND
                                    b.imc <= 30) as `Numero`
								FROM `tabla_hechos` a, `dim_paciente` b
                                WHERE (a.uci = 1) AND (a.id_paciente = b.id)";
					$exec = mysqli_query($con, $query);
					while($row = mysqli_fetch_array($exec)) {
						echo "['".$row['Enfermedad']."',".$row['Numero']."],";
					}
				?>
				]);

			var options = {
				pieHole: 0.45,
				pieSliceTextStyle: {
					color: 'black',
				},
				legend: 'right',
				is3D: false,
				colors: ['#ED9390', '#55DBCB']
			};

			var chart = new google.visualization.PieChart(document.getElementById("PieChart"));

			chart.draw(data, options);
		}

	</script>

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Practica 3 SSI</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse"></div>
		</div>
	</nav>

	<div class="container-fluid bg-general">
		<div class="row">
			<div class="col-sm-12 col-md-12">
				<h1 class="page-header" style="margin-top:60px"></h1>
				<div class="row">
					<div class="col-sm-1 col-md-1"></div>
					<div class="col-sm-10 col-md-10">
						<h3 class="title-chart">Numero de pacientes (hombre/mujer) por hospital</h3>
						<div id="table_div" style="width: 100%; height: 100%;"></div>
					</div>
					<div class="col-sm-1 col-md-1"></div>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 40px;">
			<div class="col-sm-12 col-md-12">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-sm-12 col-md-5">
						<h3 class="title-chart">Numero de pacientes en la UCI por hospital</h3>
						<div id="BarChartUCI" style="width: 100%; height: 100%;"></div>
					</div>
					<div class="col-sm-12 col-md-5">
						<h3 class="title-chart">Numero de pacientes fallecidos por hospital</h3>
						<div id="BarChartFallecido" style="width: 100%; height: 100%;"></div>
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 40px;">
			<div class="col-sm-12 col-md-12">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-sm-12 col-md-5">
						<h3 class="title-chart">Rango de edades de pacientes en la UCI</h3>
						<div id="HistogramChart" style="width: 100%; height: 100%;"></div>
					</div>
					<div class="col-sm-12 col-md-5">
						<h3 class="title-chart">Numero de pacientes en la UCI con alguna/ninguna enfermedad</h3>
						<div id="PieChart" style="width: 100%; height: 100%;"></div>
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 40px;"></div>
	</div>
</body>
</html>
