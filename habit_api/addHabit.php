<?php
header("Content-Type: application/json");

$conn = new mysqli("localhost", "root", "", "projectanmp");

if ($conn->connect_error) {
    die(json_encode(["error" => "Koneksi gagal"]));
}

$title = $_POST['title'];
$description = $_POST['description'];
$target = $_POST['target'];
$unit = $_POST['unit'];
$icon = $_POST['icon'];
$status = "In Progress";
$progress = 0;


$sql = "INSERT INTO habits (title, description, target, unit, progress, icon, status)
VALUES ('$title', '$description', '$target', '$unit', '$progress', '$icon', '$status')";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Habit berhasil ditambahkan"]);
} else {
    echo json_encode(["error" => $conn->error]);
}

$conn->close();
?>