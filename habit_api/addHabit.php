<?php
header("Content-Type: application/json");

$conn = new mysqli("localhost", "root", "", "projectanmp");

if ($conn->connect_error) {
    die(json_encode(["error" => "Koneksi gagal"]));
}

$title = $_POST['title'];
$description = $_POST['description'];
$target = $_POST['target'];
$progress = $_POST['progress'];
$icon = $_POST['icon'];
$status = $_POST['status'];

$sql = "INSERT INTO habits (title, description, target, progress, icon, status)
VALUES ('$title', '$description', '$target', '$progress', '$icon', '$status')";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Habit berhasil ditambahkan"]);
} else {
    echo json_encode(["error" => $conn->error]);
}

$conn->close();
?>