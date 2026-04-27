<?php
header("Content-Type: application/json");

$conn = new mysqli("localhost", "root", "", "projectanmp");

if ($conn->connect_error) {
    die(json_encode(["error" => "Koneksi gagal"]));
}

$id = $_POST['id'];
$progress = $_POST['progress'];
$status = $_POST['status'];

$sql = "UPDATE habits 
        SET progress='$progress', status='$status' 
        WHERE id='$id'";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Habit berhasil diupdate"]);
} else {
    echo json_encode(["error" => $conn->error]);
}

$conn->close();
?>