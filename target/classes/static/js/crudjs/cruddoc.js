const addDoc = document.getElementById('add-doc');
const updateDoc = document.getElementById('update-doc');
const findDoc = document.getElementById('find-doc');
const deleteDoc = document.getElementById('delete-doc');
const allDoc = document.getElementById('all-doc');

updateDoc.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';

    // if (event.target === ch)


});

updateDoc.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

addDoc.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

addDoc.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

findDoc.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

findDoc.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

deleteDoc.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

deleteDoc.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});

allDoc.addEventListener('mouseenter', function (event) {
    event.target.style.transform = 'scale(1.2)';
    event.target.style.backgroundColor = '#d3e1f6';
    event.target.style.transition = 'transform 0.3 ease';
});

allDoc.addEventListener('mouseleave', function (event) {
    event.target.style.transform = 'scale(1)';
    event.target.style.backgroundColor = 'white';
});
