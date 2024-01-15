import matplotlib.pyplot as plt
import networkx as nx

def dijkstra(graph, start):
    # Инициализация
    distances = {vertex: float('infinity') for vertex in graph}
    distances[start] = 0

    # Очередь с приоритетами
    priority_queue = [(0, start)]

    while priority_queue:
        current_distance, current_vertex = priority_queue.pop(0)

        # Проверка ближайших вершин
        for neighbor, weight in graph[current_vertex].items():
            distance = current_distance + weight
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                priority_queue.append((distance, neighbor))
                priority_queue.sort()

    return distances

graph = {
    'A': {'B': 1, 'C': 4},
    'B': {'A': 1, 'C': 2, 'D': 5},
    'C': {'A': 4, 'B': 2, 'D': 1},
    'D': {'B': 5, 'C': 1}
}

start_vertex = 'A'
result = dijkstra(graph, start_vertex)
print(f"Shortest distances from {start_vertex}: {result}")



def plot_graph(graph):
    G = nx.Graph()

    for vertex, neighbors in graph.items():
        G.add_node(vertex)
        for neighbor, weight in neighbors.items():
            G.add_edge(vertex, neighbor, weight=weight)

    pos = nx.spring_layout(G)
    nx.draw(G, pos, with_labels=True)
    labels = nx.get_edge_attributes(G, 'weight')
    nx.draw_networkx_edge_labels(G, pos, edge_labels=labels)
    plt.show()

# Пример графа
example_graph = {
    'A': {'B': 1, 'C': 4},
    'B': {'A': 1, 'C': 2, 'D': 5},
    'C': {'A': 4, 'B': 2, 'D': 1},
    'D': {'B': 5, 'C': 1}
}

plot_graph(example_graph)