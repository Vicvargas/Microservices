package CompressionMicroservice.service;

import CompressionMicroservice.model.Node;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Service
public class NodeService {
    Node buildTreeWithStatistics(Map<String, Integer> statisticsMap) {

        PriorityQueue<Node> nodes = statisticsMap.entrySet()
                .stream()
                .map(s -> new Node(s.getKey(), s.getValue()))
                .collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.comparing(Node::getFrequency))));

        while(nodes.size() > 1) {

            Node n1 = nodes.poll();
            Node n2 = nodes.poll();

            Node index = new Node();
            index.setLeft(n1);
            index.setRight(n2);
            index.setFrequency(n1.getFrequency() + n2.getFrequency());

            nodes.add(index);
        }

        return nodes.poll();
    }

    Node buildTreeWithMapping(Map<String, String> mapping) {
        Node root = new Node();

        for (Map.Entry<String, String> codeMapEntry : mapping.entrySet()) {
            char[] keyChar = codeMapEntry.getValue().toCharArray();

            Node index = root;
            for (char aKeyChar : keyChar) {
                if ('0' == aKeyChar) {
                    if (index.getLeft() == null) {
                        index.setLeft(new Node());
                    }
                    index = index.getLeft();
                } else {
                    if (index.getRight() == null) {
                        index.setRight(new Node());
                    }
                    index = index.getRight();
                }
            }
            index.setValue(codeMapEntry.getKey());
        }

        return root;
    }

    String decodeEncodedStr(Node root, String encoded) {

        StringBuilder sb = new StringBuilder();

        Node index = root;
        char[] encodedCharArr = encoded.toCharArray();
        for (char anEncodedCharArr : encodedCharArr) {

            index = '0' == anEncodedCharArr ? index.getLeft() : index.getRight();
            if (index.isLeap()) {
                sb.append(index.getValue());
                index = root;
            }
        }

        return sb.toString();
    }

    Map<String, String> findMapping(Node node) {
        return findMapping(node, "", new HashMap<>());
    }

    String encodeDecodedStr(Map<String, String> mapping, String text) {

        String encodedText = text;
        for (Map.Entry<String, String> mappingEntry : mapping.entrySet()) {

            while(encodedText.contains(mappingEntry.getKey())) {
                encodedText = encodedText.replace(mappingEntry.getKey(), mappingEntry.getValue());
            }
        }
        return encodedText;
    }

    private Map<String, String> findMapping(Node node, String str, Map<String, String> mapping) {

        if (node != null) {

            if (node.getValue() != null) {
                mapping.put(node.getValue(), str);
            }

            findMapping(node.getLeft(), str + "0", mapping);
            findMapping(node.getRight(), str + "1", mapping);
        }
        return mapping;
    }
}
