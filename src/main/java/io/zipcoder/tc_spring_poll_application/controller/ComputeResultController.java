package io.zipcoder.tc_spring_poll_application.controller;

import dtos.OptionCount;
import dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.domain.Option;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@RestController
public class ComputeResultController {
    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);
        Collection<OptionCount> results = new ArrayList<>();
//        //polls have sets of options
//            //We have accessed the poll and received all of the votes
//            //that are associated under that pollId.
//        //Each option has a unique id
//        //Each vote contains the option that was voted for
//        /*
//         * What we need to do is go through the votes and count
//         * the individual optionIDs. To do so, we need to be able
//         * to do the following:
//         *      Check the results collection to see if it contains
//         *          a OptionCount object for that particular
//         *          optionId already
//         *      If it has one, increment it. If not, create it and
//         *          set its count to 1, then add it to the Collection
//         */
        int totalVotes=0;
        for (Vote v : allVotes){
            totalVotes++;
            boolean lacksExistingOptionCount=true;

            for (OptionCount oc : results){
                if (oc.getOptionId().equals(
                       v.getOption().getId()) )
                {
                    oc.setCount(oc.getCount()+1);
                    lacksExistingOptionCount=false;
                    break;
                }
            }
            if (lacksExistingOptionCount){
                OptionCount oc = new OptionCount();
                oc.setOptionId(v.getOption().getId());
                oc.setCount(1);
                results.add(oc);
            }
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(results);

        return new ResponseEntity<>(voteResult, HttpStatus.OK);
    }
}
