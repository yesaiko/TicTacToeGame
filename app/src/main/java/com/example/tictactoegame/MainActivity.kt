package com.example.tictactoegame

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {
        val buSelected = view as Button
        var cellID = 0
        when (buSelected.id) {
            R.id.button1 -> cellID = 1
            R.id.button2 -> cellID = 2
            R.id.button3 -> cellID = 3
            R.id.button4 -> cellID = 4
            R.id.button5 -> cellID = 5
            R.id.button6 -> cellID = 6
            R.id.button7 -> cellID = 7
            R.id.button8 -> cellID = 8
            R.id.button9 -> cellID = 9
        }
        playGame(cellID, buSelected)

    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var active_player = 1


    private fun playGame(cellID: Int, buSelected: Button) {
        if (active_player == 1) {
            buSelected.text = "✕"
            buSelected.setTextColor((Color.parseColor("#d9a5b3")))
            buSelected.setBackgroundColor(Color.parseColor("#8a307f"))
            player1.add(cellID)
            active_player = 2
        }
        else {
            buSelected.text = "◯"
            buSelected.setTextColor(Color.parseColor("#8a307f"))
            buSelected.setBackgroundColor((Color.parseColor("#d9a5b3")))
            player2.add(cellID)
            active_player = 1
        }

        buSelected.isEnabled = false

        if(WhoIsTheWinner() == 0)
            if(active_player == 2) ComputerPlay()

    }

    private fun WhoIsTheWinner(): Int {
        var winner = -1

        //column1
        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }

        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //column2
        //1,5,9
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }

        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //3,6,9
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //1,4,7
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //1,5,9
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2

        }

        //3,5,7
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2

        }

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Winner")
        if (winner != -1) {
            if (winner == 1) {
                //Toast.makeText(this, "Player 1 won the game.", Toast.LENGTH_SHORT).show()
                builder.setMessage("You won the game. Congrats!")
                builder.setPositiveButton("OK"){dialog, which -> finish()}
                val dialog: AlertDialog = builder.create()
                dialog.show()



            } else
            {
                builder.setMessage("Computer won the game. Restart the application and try again!")
                builder.setPositiveButton("OK"){dialog, which -> finish()}
                val dialog: AlertDialog = builder.create()
                dialog.show()

            }
            return winner
        }

        return 0
    }

    private fun ComputerPlay(){
        var emptyCells = ArrayList<Int>()
        for(cellID in 1..9){
            if(!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCells.add(cellID)
            }

        }
        val r = Random()
        val randIndex = r.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]

        val buSelected:Button
        buSelected = when(cellID){
            1-> button1
            2-> button2
            3-> button3
            4-> button4
            5-> button5
            6-> button6
            7-> button7
            8-> button8
            9-> button9
            else-> button1
        }
        playGame(cellID,buSelected)
    }


}