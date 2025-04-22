const addEmpl = document.getElementById('add-empl');
const updateEmpl = document.getElementById('update-empl');
const findEmpl = document.getElementById('find-empl');
const deleteEmpl = document.getElementById('delete-empl');
const allEmpl = document.getElementById('all-empl');
const reportEmpl = document.getElementById('report-empl');


addEmpl.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';

    const btn = document.getElementById('add-empl-btn');

    btn.addEventListener("mouseenter", function (e) {
        e.target.style.transform = 'scale(1)';
        e.target.style.backgroundColor = 'white';
    });
});

addEmpl.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

updateEmpl.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

updateEmpl.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

findEmpl.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

findEmpl.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

deleteEmpl.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

deleteEmpl.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

allEmpl.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

allEmpl.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

reportEmpl.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

reportEmpl.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});
