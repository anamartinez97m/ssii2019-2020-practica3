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
		google.charts.load("current", {packages:["table"]});
		google.setOnLoadCallback(drawTable);

		function drawTable() {
			var data = google.visualization.arrayToDataTable([
				['Hospital', 'Pacientes'],
				<?php
					$query = "SELECT `id_hospital` as `Hospital`,
                              		COUNT(`id_paciente`) as `Pacientes`
                              FROM `tabla_hechos`
                              GROUP BY `id_hospital` ";
					$exec = mysqli_query($con, $query);
					while($row = mysqli_fetch_array($exec)) {
						echo "['".$row['Hospital']."',".$row['Pacientes']."],";
					}
				?>
			]);

			var table = new google.visualization.Table(document.getElementById('table_div1'));

			table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
		}

	</script>
</head>
<body>

<div class="container-fluid">
    <div id="table_div1" style="width: 200px; height: 500px; float:left;"></div>
</div>

</body>
</html>