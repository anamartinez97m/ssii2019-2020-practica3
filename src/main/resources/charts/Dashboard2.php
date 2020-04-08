<?php
	$con = mysqli_connect('localhost', 'newuser', 'URJC2019!', 'practica3', '3306');
?>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Dashboards Practica 3</title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">
		google.charts.load("current", {packages:["corechart"]});
		google.setOnLoadCallback(drawChart);

		function drawChart() {
			var data = google.visualization.arrayToDataTable([
				['Sexo', 'Pacientes'],
				<?php
					$query = "SELECT b.sexo as `Sexo`, COUNT(DISTINCT a.id_paciente) as `Pacientes`
								FROM `tabla_hechos` a, `dim_paciente` b
								WHERE a.id_paciente = b.id
								GROUP BY b.sexo";
					$exec = mysqli_query($con, $query);
					while($row = mysqli_fetch_array($exec)) {
						echo "['".$row['Sexo']."',".$row['Pacientes']."],";
					}
				?>
				]);

			var options = {
				title: 'Sexos de pacientes',
				pieHole: 0.5,
				pieSliceTextStyle: {
					color: 'black',
				},
				legend: 'top',
				is3D: false
			};

			var chart = new google.visualization.PieChart(document.getElementById('PieChart'));

			chart.draw(data, options);
		}

	</script>
</head>
<body>

<div class="container-fluid">
    <div id="PieChart" style="width: 200px; height: 500px; float:left;"></div>
</div>

</body>
</html>