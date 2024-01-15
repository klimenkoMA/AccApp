from frontend.pythonlabs.Network import Network, Node
import networkx as nx
import matplotlib.pyplot as plt

# Создаем пустой граф
G = nx.Graph()

# Создаем сеть с узлами и уровнями
network = Network()
nodeA = Node("Node A", 1)
nodeB = Node("Node B", 1)
nodeC = Node("Node C", 2)
nodeD = Node("Node D", 2)

network.add_node(nodeA)
network.add_node(nodeB)
network.add_node(nodeC)
network.add_node(nodeD)

# Добавляем узлы в граф
for node in network.nodes:
    G.add_node(node.name, level=node.level)

# Добавляем ребра между узлами
G.add_edge(nodeA.name, nodeB.name)
G.add_edge(nodeB.name, nodeC.name)
G.add_edge(nodeC.name, nodeD.name)

# Определяем позиции для узлов на графе
pos = nx.spring_layout(G)

# Рисуем узлы и ребра
nx.draw_networkx_nodes(G, pos)
nx.draw_networkx_edges(G, pos)

# Добавляем метки узлов
labels = {node.name: node.name for node in network.nodes}
nx.draw_networkx_labels(G, pos, labels)

# Выводим граф
plt.title("Hierarchical Routing Network")
plt.axis("off")
plt.show()
