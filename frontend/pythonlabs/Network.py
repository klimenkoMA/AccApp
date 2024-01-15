from pip._internal.cli.cmdoptions import python

python

class Node:
    def __init__(self, name, level):
        self.name = name
        self.level = level

class Network:
    def __init__(self):
        self.nodes = []

    def add_node(self, node):
        self.nodes.append(node)


class HierarchicalRouter:
    def find_optimal_route(self, network, source, destination):
        optimal_route = []

        # Пример простой логики выбора маршрута - выбираем узлы на том же уровне
        for node in network.nodes:
            if node.level == source.level and node != source:
                optimal_route.append(node)

        # Добавляем узел назначения в конец маршрута
        optimal_route.append(destination)

        return optimal_route

# Создаем сеть
network = Network()

# Добавляем узлы
nodeA = Node("Node A", 1)
nodeB = Node("Node B", 1)
nodeC = Node("Node C", 2)
nodeD = Node("Node D", 2)

network.add_node(nodeA)
network.add_node(nodeB)
network.add_node(nodeC)
network.add_node(nodeD)

# Создаем экземпляр маршрутизатора
router = HierarchicalRouter()

# Устанавливаем начальный и конечный узлы
source = nodeA
destination = nodeD

# Находим оптимальный маршрут
optimal_route = router.find_optimal_route(network, source, destination)

# Выводим результат
print(f"Optimal Route from {source.name} to {destination.name}:")
for node in optimal_route:
    print(node.name)
