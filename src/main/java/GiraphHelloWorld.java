import org.apache.giraph.aggregators.matrix.dense.IntDenseVector;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.GiraphRunner;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class GiraphHelloWorld extends
        BasicComputation<Text, IntWritable, IntWritable, NullWritable> {

    @Override
    public void compute(Vertex<Text, IntWritable, IntWritable> vertex,
                        Iterable<NullWritable> messages) throws IOException {

        if(getSuperstep() < 2) {
            System.out.println("vertex compute");
            for(int i=0; i<2; i++) {
                for(int j=0; j<3; j++) {
                    TotalNumberOfEdgesMC.agg.aggregate(i, j, i+j, this);
                }
            }
        } else {
            vertex.voteToHalt();
        }

//        System.out.print("Hello world from: " + vertex.getId().toString() + " who is following :");
//
//        for(Edge<Text, IntWritable> e: vertex.getEdges()) {
//            System.out.print(" " + e.getTargetVertexId() + ",");
//        }
//        System.out.println("");

//        vertex.voteToHalt();
    }
}