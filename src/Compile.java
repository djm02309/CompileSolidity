import java.io.*;
import java.io.*;
public class Compile {
        public static void main(String[] args) {
            Compile cp = new Compile();
            File dir = new File("/home/ether/Downloads/realData/AfterCompile");
            //String Directory = "/home/ether/Downloads/solidity코드/page";
            File[] fileList = dir.listFiles();  //pages디렉토리들

            try {
                for (int i = 0; i < fileList.length; i++) {
                    File file = fileList[i]; //page 디렉토리 하나
                    if (file.isDirectory()) {  //디렉토리 이면
                        File[] txts = FilenameFilterUtils.getfilenameFilterList(file.getPath(), ".txt");  //page_에 디렉토리 내에 파일들
                        for (int k = 0; k < txts.length; k++) {
                            File sol = txts[k];
                            String fileName = sol.getName().substring(0, sol.getName().indexOf("."));
                            String binFilePathName = sol.getPath().substring(0, sol.getPath().lastIndexOf("/") + 1) + fileName + ".bin";
                            String abiFilePathName = sol.getPath().substring(0, sol.getPath().lastIndexOf("/") + 1) + fileName + ".abi";
                            File binFile = new File(sol.getPath().substring(0, sol.getPath().lastIndexOf("/") + 1) + fileName + ".bin");
                            File abiFile = new File(sol.getPath().substring(0, sol.getPath().lastIndexOf("/") + 1) + fileName + ".abi");
//                    File runtimeFile = new File(sol.getPath().substring(0,sol.getPath().lastIndexOf("/")+1)+"runtime");
                            if (sol.isFile() && !binFile.exists()) {// 파일이 있다면 파일 이름 출력
                                String version = cp.checkVersion(sol);
                                if (version.equals("5")) {
                                    cp.compileBin5(sol, binFile);
                                    cp.compileAbi5(sol, abiFile);
//                            System.out.println("\t 파일 이름 = " + sol.getName());
                                } else if (version.equals("4")) {
                                    cp.compileBin4(sol, binFile);
                                    cp.compileAbi4(sol, abiFile);
                                } else {
                                    System.out.println("check pragma of " + sol.getName() + " at " + sol.getPath());
                                }
                            }
                            System.out.println(sol.getName() + " done. ");
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
            }
            //출처: https://ra2kstar.tistory.com/133 [초보개발자 이야기.]
        }

        public void compileBin5(File file, File BinFile) throws IOException, InterruptedException {
            ProcessBuilder builder = new ProcessBuilder("/home/ether/solidity/build/solc/./solc", "--bin", file.getAbsolutePath());
            builder.directory(new File("/home/ether/solidity/build/solc"));
            Process p = builder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String result = "";
            String addResult = "";
            while ((result = br.readLine()) != null) {
                addResult += result + "\n";
            }
//            if(error!= null && !error.readLine().contains("pre")){
//                System.out.println("error: "+error.readLine() );
//            }else{
//                System.out.println("Bin: "+addResult + " done. ");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(BinFile));
                    bw.write(addResult);
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }
            br.close();
//        System.out.println(addResult);
            //        출처: https://wildcoots.tistory.com/entry/자바프로그램으로-외부-프로그램-실행-하기 [하늘을 닮고싶은 늑대...]



//        출처: https://wildcoots.tistory.com/entry/자바프로그램으로-외부-프로그램-실행-하기 [하늘을 닮고싶은 늑대...]
        }

        public void compileAbi5(File file, File AbiFile) throws IOException, InterruptedException {
            ProcessBuilder builder = new ProcessBuilder("/home/ether/solidity/build/solc/./solc", "--abi", file.getPath());
            builder.directory(new File("/home/ether/solidity/build/solc"));
            Process p = builder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String result = "";
            String addResult = "";
            while ((result = br.readLine()) != null) {

                addResult += result + "\n";
            }
//            if(error != null){
//                System.out.println("error: "+error.readLine() );
//            }else{
//                System.out.println("Bin: "+addResult + " done. ");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(AbiFile));
                    bw.write(addResult);
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }
            br.close();
//        System.out.println(addResult);
            //        출처: https://wildcoots.tistory.com/entry/자바프로그램으로-외부-프로그램-실행-하기 [하늘을 닮고싶은 늑대...]
//        출처: https://wildcoots.tistory.com/entry/자바프로그램으로-외부-프로그램-실행-하기 [하늘을 닮고싶은 늑대...]
        }

        public void compileBin4(File file, File BinFile) throws IOException {
            ProcessBuilder builder = new ProcessBuilder("/home/ether//Desktop/solidity_0.4.26/build/solc/./solc", "--bin", file.getPath());
            builder.directory(new File("/home/ether//Desktop/solidity_0.4.26/build/solc/"));
            Process p = builder.start();


            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String result = "";
            String addResult = "";
            while ((result = br.readLine()) != null) {
                addResult += result + "\n";
            }
            br.close();
//        System.out.println(addResult);
            //        출처: https://wildcoots.tistory.com/entry/자바프로그램으로-외부-프로그램-실행-하기 [하늘을 닮고싶은 늑대...]
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(BinFile));
                bw.write(addResult);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //출처:https://qkrrudtjr954.github.io/java/2017/11/13/file-write.html

        }

        public void compileAbi4(File file, File AbiFile) throws IOException {
            ProcessBuilder builder = new ProcessBuilder("/home/ether//Desktop/solidity_0.4.26/build/solc/./solc", "--abi", file.getPath());
            builder.directory(new File("/home/ether//Desktop/solidity_0.4.26/build/solc/"));
            Process p = builder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String result = "";
            String addResult = "";
            while ((result = br.readLine()) != null) {
                addResult += result + "\n";
            }
            br.close();
//        System.out.println(addResult);
            //        출처: https://wildcoots.tistory.com/entry/자바프로그램으로-외부-프로그램-실행-하기 [하늘을 닮고싶은 늑대...]
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(AbiFile));
                bw.write(addResult);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

//        출처: https://wildcoots.tistory.com/entry/자바프로그램으로-외부-프로그램-실행-하기 [하늘을 닮고싶은 늑대...]
        }

        public String checkVersion(File file) {
            String a = "";
            try {
                //입력 스트림 생성
                FileReader filereader = new FileReader(file);
                BufferedReader bufReader = new BufferedReader(filereader);
                String line = "";
                while ((line = bufReader.readLine()) != null) {
                    if (line.contains("pragma")) {
                        a = line.substring(line.indexOf(".") + 1, line.indexOf(".") + 2);  ///이부분 수정
                        break;
                    }
                }
                //.readLine()은 끝에 개행문자를 읽지 않는다.
                bufReader.close();
            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
            return a;
        }
}
