<?php
header("Content-Type: application/json");

// koneksi database
$conn = new mysqli("localhost", "root", "", "projectanmp");

if ($conn->connect_error) {
    die(json_encode(["error" => "Koneksi gagal"]));
}

$result = $conn->query("SELECT * FROM habits");

$data = array();

while($row = $result->fetch_assoc()) {
    $data[] = $row;
}

echo json_encode($data);

$conn->close();
?>