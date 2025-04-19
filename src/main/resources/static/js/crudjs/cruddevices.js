const addDevice = document.getElementById('add-device');
const updateDevice = document.getElementById('update-device');
const findDevice = document.getElementById('find-device');
const deleteDevice = document.getElementById('delete-device');
const allDevices = document.getElementById('all-devices');
const reportDevice = document.getElementById('report-device');

updateDevice.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

updateDevice.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

addDevice.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

addDevice.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

findDevice.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

findDevice.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

deleteDevice.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

deleteDevice.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

allDevices.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

allDevices.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

reportDevice.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

reportDevice.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});