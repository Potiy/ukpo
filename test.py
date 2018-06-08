#activate qt4
#py.test -s test.py
#Функциональное тестирование оконного интерфейса
from PyQt4 import QtCore, QtGui
import pytest
import pytestqt
from mainwindow import mainwindow

def test_basic_widget(qtbot): #автоматич. созд. объект с набором методов для тест.
    window = mainwindow()
    window.show()
    qtbot.addWidget(window) #registration of widget (for обработка)
    for char in 'a b c a b c':
        qtbot.mouseMove(window.plainTextEdit)#курсор
        qtbot.keyClicks(window.plainTextEdit, char) #заполнение 
        qtbot.wait(150) #ожидание в процессе заполнения
    qtbot.wait(2000) #между действиями 
    qtbot.mouseMove(window.pushButton)#курсор
    qtbot.mouseClick(window.pushButton, QtCore.Qt.LeftButton)#нажатие левой кнопкой мыши на оке
    qtbot.wait(150)
    qtbot.mouseMove(window.plainTextEdit_2)
    assert window.plainTextEdit_2.toPlainText() == "a b c: 2;"
    qtbot.wait(2000)
    qtbot.mouseMove(window.pushButton_2)
    qtbot.mouseClick(window.pushButton_2, QtCore.Qt.LeftButton)
    qtbot.wait(150)
    qtbot.mouseMove(window.plainTextEdit)
    assert window.plainTextEdit.toPlainText() == ''
    qtbot.wait(2000)
    window.plainTextEdit.clear()
    #window.plainTextEdit_2.clear()
    qtbot.mouseMove(window.pushButton)
    qtbot.mouseClick(window.pushButton,QtCore.Qt.LeftButton)
    assert window.plainTextEdit_2.toPlainText() == 'введите строку'
    qtbot.wait(2000)