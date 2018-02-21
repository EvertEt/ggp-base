package org.ggp.base.player.gamer.statemachine.alphabeta;

import org.ggp.base.player.gamer.event.GamerSelectedMoveEvent;
import org.ggp.base.player.gamer.statemachine.sample.SampleGamer;
import org.ggp.base.util.statemachine.MachineState;
import org.ggp.base.util.statemachine.Move;
import org.ggp.base.util.statemachine.Role;
import org.ggp.base.util.statemachine.exceptions.GoalDefinitionException;
import org.ggp.base.util.statemachine.exceptions.MoveDefinitionException;
import org.ggp.base.util.statemachine.exceptions.TransitionDefinitionException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Alpha Beta player
 * https://docs.google.com/document/d/1Rm_sjou46e4FlbrheCaoig21CkV0eJPexavRhT09YUA/edit
 */
public final class AlphaBetaGamer extends SampleGamer {

    /**
     * This function is called at the start of each round
     * You are required to return the Move your player will play
     * before the timeout.
     */
    @Override
    public Move stateMachineSelectMove(long timeout) throws TransitionDefinitionException, MoveDefinitionException, GoalDefinitionException {
        long start = System.currentTimeMillis();
        List<Move> moves = getStateMachine().getLegalMoves(getCurrentState(), getRole());
        if (moves.get(0).toString().equals("noop")) {
            return moves.get(0);
        }

        System.out.println("Start thinking...");
        System.out.println(moves);
        moves = new ArrayList<>(moves);
        Collections.shuffle(moves);
        Move selection = bestMove(getRole(), getCurrentState(), moves);

        long stop = System.currentTimeMillis();

        notifyObservers(new GamerSelectedMoveEvent(moves, selection, stop - start));
        System.out.println(selection);
        System.out.println("Done thinking...");
        return selection;
    }

    private Move bestMove(Role role, MachineState state, List<Move> moves) throws TransitionDefinitionException, MoveDefinitionException, GoalDefinitionException {
        int lowerThreshold = 0;
        int upperThreshold = 100;

        Move best = moves.get(0);
        int score = 0;
        int alpha = lowerThreshold;
        int beta = upperThreshold + 1;

        for (Move move : moves) {
            int result = minScore(role, move, state, alpha, beta);
            if (result == upperThreshold) return move; // Win is perfect
            if (result > score) {
                score = result;
                best = move;
            }
        }
        return best;
    }

    private int minScore(Role role, Move move, MachineState state, int alpha, int beta) throws TransitionDefinitionException, MoveDefinitionException, GoalDefinitionException {
        List<List<Move>> allMoveCombos = getStateMachine().getLegalJointMoves(state, role, move);

        // decide best future state given all move combinations
        for (List<Move> allMoveCombo : allMoveCombos) {
            MachineState candidateState = getStateMachine().getNextState(state, allMoveCombo);
            //pick highest candidateState
            int result = maxScore(role, candidateState, alpha, beta);
            beta = Math.min(beta, result);
            if (beta <= alpha) {
                return alpha; // fail-hard
            }
        }
        return beta;
    }

    private int maxScore(Role role, MachineState state, int alpha, int beta) throws TransitionDefinitionException, MoveDefinitionException, GoalDefinitionException {
        if (getStateMachine().isTerminal(state)) {
            return getStateMachine().getGoal(state, role);
        }

        List<Move> moves = getStateMachine().getLegalMoves(state, role);

        for (Move move : moves) {
            int result = minScore(role, move, state, alpha, beta);
            alpha = Math.max(alpha, result);
            if (alpha >= beta) {
                return beta;
            }
        }
        return alpha;
    }
}