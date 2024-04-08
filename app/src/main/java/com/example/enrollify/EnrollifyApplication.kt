package com.example.enrollify

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.enrollify.data.Course
import com.example.enrollify.data.EnrollifyAppContainer
import com.example.enrollify.data.EnrollifyAppDataContainer
import com.example.enrollify.data.EnrollifyDatabase
import com.example.enrollify.data.Prerequisite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class EnrollifyApplication : Application() {

    lateinit var container: EnrollifyAppContainer

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {

        super.onCreate()
        container = EnrollifyAppDataContainer(this)


//        var coursesExist = false
        val db = Room.databaseBuilder(
            applicationContext,
            EnrollifyDatabase::class.java, "enrollify_database"
        ).build()


        generateFakeData(db)


    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun generateFakeData(db: EnrollifyDatabase) {
        GlobalScope.launch {


            val courseDataList = listOf(
                Course(
                    courseUniqueId = "CS128",
                    name = "Introduction to Coding",
                    professor = "Dr. John Smith",
                    term = 2,
                    about = "This course is designed to introduce students to the fundamentals of coding. Students will learn programming concepts such as variables, loops, conditionals, and functions. The course will also cover basic data structures and algorithms.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS135",
                    name = "Computer Application Technology",
                    professor = "Prof. Emily Johnson",
                    term = 1,
                    about = "Computer Application Technology is a comprehensive course that covers various applications of computer technology in real-world scenarios. Students will explore topics such as office productivity software, databases, internet technologies, and multimedia applications.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS161",
                    name = "Introduction to Programming",
                    professor = "Dr. Michael Anderson",
                    term = 1,
                    about = "Introduction to Programming is an introductory course that provides students with a solid foundation in programming concepts and problem-solving techniques. Topics covered include basic syntax, control structures, arrays, and object-oriented programming principles.",
                    isCompleted = true,
                    isRegistered = false
                ), Course(
                    courseUniqueId = "CS162",
                    name = "Programming and Data Structure",
                    professor = "Dr. Sarah Johnson",
                    term = 2,
                    about = "Programming and Data Structure is an intermediate-level course that focuses on data structures and algorithms. Students will learn about fundamental data structures such as arrays, linked lists, stacks, queues, trees, and graphs. The course will also cover algorithm design and analysis techniques.",
                    isCompleted = true,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS215",
                    name = "Social Issues",
                    professor = "Prof. David Williams",
                    term = 2,
                    about = "Social Issues is an interdisciplinary course that examines various social issues from a technological perspective. Topics covered include ethics, privacy, security, diversity, and the impact of technology on society. Students will explore case studies and engage in discussions to gain a deeper understanding of these issues.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS225",
                    name = "Health Analytics",
                    professor = "Dr. Lisa Miller",
                    term = 1,
                    about = "Health Analytics is a specialized course that focuses on the application of data analytics in healthcare. Students will learn about healthcare data sources, data preprocessing techniques, predictive modeling, and healthcare analytics tools. The course will also cover ethical considerations and regulatory requirements in healthcare analytics.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS223",
                    name = "Data Science",
                    professor = "Dr. Kevin Brown",
                    term = 1,
                    about = "Data Science is an interdisciplinary field that combines statistical analysis, machine learning, and domain knowledge to extract insights from data. This course provides students with a comprehensive introduction to data science concepts, methodologies, and tools. Topics covered include data exploration, data preprocessing, feature engineering, predictive modeling, and data visualization.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS255",
                    name = "Advanced Data Structure",
                    professor = "Prof. Laura Clark",
                    term = 0,
                    about = "Advanced Data Structure is an advanced-level course that explores advanced data structures and algorithms. Students will study advanced topics such as balanced trees, hash tables, priority queues, disjoint-set data structures, and graph algorithms. The course will also cover advanced algorithm design techniques and analysis methods.",
                    isCompleted = true,
                    isRegistered = false,
                ),
                Course(
                    courseUniqueId = "CS263",
                    name = "Computer Architecture and Organization",
                    professor = "Dr. Christopher White",
                    term = 0,
                    about = "Computer Architecture and Organization is a foundational course that provides students with an understanding of computer hardware and its organization. Topics covered include digital logic, processor architecture, memory systems, input/output systems, and instruction set architecture. The course will also cover performance evaluation and optimization techniques.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS275",
                    name = "Database Management Systems",
                    professor = "Prof. Jennifer Harris",
                    term = 2,
                    about = "Database Management Systems is a comprehensive course that covers the principles and techniques of database management. Students will learn about relational database concepts, database design, SQL programming, transaction management, and database administration. The course will also cover advanced topics such as data warehousing, data mining, and distributed databases.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS277",
                    name = "Discrete Structure",
                    professor = "Dr. Richard Martinez",
                    term = 1,
                    about = "Discrete Structure is a fundamental course that covers mathematical concepts essential for computer science. Topics covered include sets, relations, functions, logic, proof techniques, induction, combinatorics, and graph theory. The course will also cover applications of discrete mathematics in computer science.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS340",
                    name = "Evolutionary Computation",
                    professor = "Prof. Jessica Lee",
                    term = 1,
                    about = "Evolutionary Computation is a specialized course that explores computational techniques inspired by biological evolution. Students will learn about evolutionary algorithms, genetic algorithms, genetic programming, and evolutionary strategies. The course will also cover applications of evolutionary computation in optimization, machine learning, and artificial intelligence.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS355",
                    name = "Algorithm Design and Analysis",
                    professor = "Dr. Robert Thompson",
                    term = 1,
                    about = "Algorithm Design and Analysis is a core course that covers fundamental algorithms and algorithmic techniques. Students will learn about algorithm design paradigms, algorithm analysis techniques, and computational complexity theory. Topics covered include sorting algorithms, searching algorithms, dynamic programming, greedy algorithms, and graph algorithms.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS356",
                    name = "Theory of Computing",
                    professor = "Prof. Kimberly Wilson",
                    term = 0,
                    about = "Theory of Computing is a theoretical course that explores the foundations of computer science. Students will study formal languages, automata theory, computability theory, and complexity theory. The course will also cover applications of theoretical concepts in various areas of computer science.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS345",
                    name = "Computer Graphics",
                    professor = "Dr. Daniel Garcia",
                    term = 2,
                    about = "Computer Graphics is an applied course that explores the principles and techniques of computer graphics. Students will learn about 2D graphics, 3D graphics, rendering techniques, geometric modeling, and animation. The course will also cover graphics APIs and shader programming.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS364",
                    name = "Mobile App Development",
                    professor = "Prof. Melissa Robinson",
                    term = 1,
                    about = "Mobile App Development is a practical course that teaches students how to develop mobile applications for iOS and Android platforms. Students will learn about mobile app design principles, user interface development, data management, and app deployment. The course will also cover mobile app testing and performance optimization.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS368",
                    name = "Data Communications and Networking",
                    professor = "Dr. Benjamin Carter",
                    term = 1,
                    about = "Data Communications and Networking is a comprehensive course that covers the principles and protocols of data communication and networking. Students will learn about network architectures, transmission media, network protocols, routing algorithms, and network security. The course will also cover emerging technologies such as cloud computing, Internet of Things (IoT), and software-defined networking (SDN).",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS375",
                    name = "Operating Systems",
                    professor = "Dr. Amanda Martinez",
                    term = 1,
                    about = "Operating Systems is a core course that covers the principles and concepts of operating systems. Students will learn about process management, memory management, file systems, device management, and security. The course will also cover operating system design principles and case studies of modern operating systems.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS361",
                    name = "Natural Language Processing",
                    professor = "Prof. Samantha Davis",
                    term = 2,
                    about = "Natural Language Processing is an interdisciplinary course that combines techniques from computer science, linguistics, and artificial intelligence to enable computers to understand and process human language. Students will learn about text processing, language modeling, machine translation, sentiment analysis, and information extraction. The course will also cover applications of natural language processing in areas such as search engines, chatbots, and virtual assistants.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS467",
                    name = "Cyber Security",
                    professor = "Dr. Matthew Wilson",
                    term = 2,
                    about = "Cyber Security is a specialized course that covers the principles and practices of securing computer systems and networks against cyber threats. Students will learn about cryptography, network security, software security, access control, and incident response. The course will also cover ethical hacking techniques and security assessment methodologies.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS485",
                    name = "Software Design",
                    professor = "Prof. Laura Thompson",
                    term = 2,
                    about = "Software Design is a practical course that teaches students how to design and develop software systems. Students will learn about software architecture, design patterns, software development methodologies, and software quality assurance. The course will also cover agile development practices and software project management.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS435",
                    name = "Algorithms and Complexity",
                    professor = "Dr. Christopher Johnson",
                    term = 2,
                    about = "Algorithms and Complexity is an advanced course that explores advanced algorithms and computational complexity theory. Students will study topics such as approximation algorithms, randomized algorithms, computational complexity classes, and hardness of approximation. The course will also cover applications of advanced algorithms in various domains.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS444",
                    name = "Machine Learning",
                    professor = "Dr. Rachel Martinez",
                    term = 1,
                    about = "Machine Learning is an interdisciplinary field that combines techniques from statistics, mathematics, and computer science to enable computers to learn from data. This course provides students with a comprehensive introduction to machine learning algorithms, methodologies, and applications. Topics covered include supervised learning, unsupervised learning, reinforcement learning, and deep learning.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS495",
                    name = "Artificial Intelligence",
                    professor = "Prof. David Anderson",
                    term = 2,
                    about = "Artificial Intelligence is a specialized course that covers the principles and techniques of artificial intelligence. Students will learn about intelligent agents, problem-solving methods, knowledge representation, reasoning under uncertainty, and machine learning algorithms. The course will also cover applications of artificial intelligence in areas such as robotics, natural language processing, and computer vision.",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "Math101",
                    name = "Introduction to Maths",
                    professor = "Dr. Sarah Johnson",
                    term = 1,
                    about = "Introduction to Maths is an introductory course that covers basic mathematical concepts and techniques. Students will learn about arithmetic operations, algebraic equations, geometric shapes, and basic probability theory. The course will also cover applications of mathematics in various fields.",
                    isCompleted = true,
                    isRegistered = false
                )
            )
            courseDataList.forEach { course ->
                try {
                    db.courseDao().insert(course)
                } catch (e: Exception) {
                    Log.e("Database", "Error inserting initial data: ${e.message}")
                }
            }
            val course101 = db.courseDao().getCourseFromUnique("Math101").firstOrNull()
            val course162 = db.courseDao().getCourseFromUnique("CS162").firstOrNull()
            val course161 = db.courseDao().getCourseFromUnique("CS161").firstOrNull()
            val course225 = db.courseDao().getCourseFromUnique("CS225").firstOrNull()
            val course223 = db.courseDao().getCourseFromUnique("CS223").firstOrNull()
            val course255 = db.courseDao().getCourseFromUnique("CS255").firstOrNull()
            val course263 = db.courseDao().getCourseFromUnique("CS263").firstOrNull()
            val course275 = db.courseDao().getCourseFromUnique("CS275").firstOrNull()
            val course277 = db.courseDao().getCourseFromUnique("CS277").firstOrNull()
            val course355 = db.courseDao().getCourseFromUnique("CS355").firstOrNull()
            val course356 = db.courseDao().getCourseFromUnique("CS356").firstOrNull()
            val course345 = db.courseDao().getCourseFromUnique("CS345").firstOrNull()
            val course364 = db.courseDao().getCourseFromUnique("CS364").firstOrNull()
            val course368 = db.courseDao().getCourseFromUnique("CS368").firstOrNull()
            val course375 = db.courseDao().getCourseFromUnique("CS375").firstOrNull()
            val course361 = db.courseDao().getCourseFromUnique("CS361").firstOrNull()
            val course467 = db.courseDao().getCourseFromUnique("CS467").firstOrNull()
            val course485 = db.courseDao().getCourseFromUnique("CS485").firstOrNull()
            val course435 = db.courseDao().getCourseFromUnique("CS435").firstOrNull()
            val course444 = db.courseDao().getCourseFromUnique("CS444").firstOrNull()
            val course495 = db.courseDao().getCourseFromUnique("CS495").firstOrNull()





            if (course162 != null && course161 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course162.id,
                        prerequisiteId = course161.id
                    )
                )
            }

            if (course225 != null && course161 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course225.id,
                        prerequisiteId = course161.id
                    )
                )
            }

            if (course223 != null && course161 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course223.id,
                        prerequisiteId = course161.id
                    )
                )
            }

            if (course255 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course255.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course263 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course263.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course275 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course275.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course277 != null && course101 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course277.id,
                        prerequisiteId = course101.id
                    )
                )
            }

            if (course355 != null && course277 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course355.id,
                        prerequisiteId = course277.id
                    )
                )
            }

            if (course355 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course355.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course356 != null && course277 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course356.id,
                        prerequisiteId = course277.id
                    )
                )
            }

            if (course356 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course356.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course345 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course345.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course364 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course364.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course368 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course368.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course375 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course375.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course361 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course361.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course467 != null && course368 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course467.id,
                        prerequisiteId = course368.id
                    )
                )
            }

            if (course485 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course485.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course435 != null && course355 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course435.id,
                        prerequisiteId = course355.id
                    )
                )
            }

            if (course444 != null && course161 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course444.id,
                        prerequisiteId = course161.id
                    )
                )
            }

            if (course495 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course495.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course495 != null && course263 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course495.id,
                        prerequisiteId = course263.id
                    )
                )
            }

            if (course495 != null && course277 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course495.id,
                        prerequisiteId = course277.id
                    )
                )
            }
        }
    }
}


